package com.divide.experience.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private TypeClient typeClient;

    public JwtAuthenticationToken(String token, TypeClient typeClient) {
        super(new ArrayList<>());
        this.token = token;
        this.typeClient = typeClient;
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

    public TypeClient getTypeClient() {
        return typeClient;
    }
}
