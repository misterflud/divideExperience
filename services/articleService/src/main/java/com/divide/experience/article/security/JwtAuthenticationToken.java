package com.divide.experience.article.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;

    /**
     * Basic constructor.
     *
     * @param token token.
     */
    public JwtAuthenticationToken(String token) {
        super(new ArrayList<>());
        this.token = token;
        setDetails(token);
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
