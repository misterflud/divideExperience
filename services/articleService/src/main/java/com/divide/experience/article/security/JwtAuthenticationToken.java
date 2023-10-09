package com.divide.experience.article.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 * <p>
 * Class for both type user -- client and servce. It should be divided in future.
 * </p>
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private TypeClient typeClient;

    /**
     * Constructor.
     *
     * @param token token.
     * @param typeClient type od client.
     */
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
