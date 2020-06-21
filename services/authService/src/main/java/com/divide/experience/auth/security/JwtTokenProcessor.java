package com.divide.experience.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Component
public class JwtTokenProcessor implements TokenProcessor {

    private static final long EXPIRATIONTIME = 864_000_00; // 1 day
    private static final String LOGIN_FIELD = "login";


    @Value("${security.oauth2.web.jwt.key-value}")
    private String secret;

    @Value("${security.oauth2.article.jwt.key-value}")
    private String articleServiceSecret;

    @Override
    public String getUserFromToken(String token) {
        if (token != null) {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            String login = body.get(LOGIN_FIELD, String.class);
            return login;
        }
        return null;
    }

    @Override
    public String generateToken(String login) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put(LOGIN_FIELD, login);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return jwt;
    }

    @Override
    public UserDetails getUserDetailsForService(String token) {
        if (token.equals(articleServiceSecret)) {
            return new org.springframework.security.core.userdetails.User(
                    "ARTICLE_SERVICE",
                    "",
                    new HashSet<>()
            );
        }
        return null;
    }
}
