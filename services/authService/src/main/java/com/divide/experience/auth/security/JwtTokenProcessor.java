package com.divide.experience.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Anton Oleynikov {@literal <aoleynikov@fil-it.ru>}
 */
@Component
public class JwtTokenProcessor implements TokenProcessor {

    static final long EXPIRATIONTIME = 864_000_00; // 1 day

    private static final String LOGIN_FIELD = "login";

    @Value("${security.oauth2.web.jwt.key-value}")
    private String SECRET;

    @Override
    public String getUserFromToken(String token) {
        if (token != null) {
            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            String login = body.get(LOGIN_FIELD, String.class);
            return login;
        }
        return null;
    }

    @Override
    public String generateToken(String login) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put(LOGIN_FIELD, login);
        String JWT = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return JWT;
    }
}
