package com.divide.experience.auth.security;

import com.divide.experience.lib.api.feign.auth.UserAuthDetailsData;
import com.google.common.collect.Sets;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Component
public class JwtTokenProcessor implements TokenProcessor {

    private static final long EXPIRATIONTIME = 864_000_00; // 1 day

    private static final String ID_FIELD = "id";
    private static final String LOGIN_FIELD = "login";
    private static final String USER_NAME_FIELD = "userName";
    private static final String ROLES_FIELD = "roles";

    @Value("${security.oauth2.web.jwt.key-value}")
    private String webSecret;

    @Value("${security.oauth2.back_services.jwt.key-value}")
    private String backServicesSecret;

    @Override
    public UserAuthDetailsData getUserDataFromToken(String token) {
        try {
            return getUserAuthDetails(token);
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException(String.format("Token %s has expired", token));
        } catch (MalformedJwtException | ClaimJwtException | RequiredTypeException | SignatureException | UnsupportedJwtException e) {
            throw new BadCredentialsException(String.format("Token %s has invalid structure", token));
        }
    }

    @Override
    public String generateToken(DivideUserDetails user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put(ID_FIELD, user.getId());
        claims.put(LOGIN_FIELD, user.getEmail());
        claims.put(USER_NAME_FIELD, user.getNickName());
        claims.put(ROLES_FIELD, ((HashSet) user.getAuthorities()).stream()
            .map(a -> ((SimpleGrantedAuthority) a).getAuthority()).collect(Collectors.toSet()));

        String jwt = Jwts.builder()
            .setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(webSecret))
            .compact();
        getUserAuthDetails(jwt);

        return jwt;
    }

    @Override
    public UserDetails getUserDetailsForService(String token) {
        return null;
    }

    private UserAuthDetailsData getUserAuthDetails(String jwtToken) {
        final Claims body = Jwts
            .parser()
            .setSigningKey(TextCodec.BASE64.encode(webSecret))
            .parseClaimsJws(jwtToken).getBody();

        final Long id = body.get(ID_FIELD, Long.class);
        final String login = body.get(LOGIN_FIELD, String.class);
        final String userName = body.get(USER_NAME_FIELD, String.class);

        Set<String> roles = new HashSet<>(Sets.newHashSet((Collection<String>) body.get(ROLES_FIELD)));

        return UserAuthDetailsData
            .builder()
            .id(id)
            .login(login)
            .userName(userName)
            .roles(roles)
            .build();
    }
}
