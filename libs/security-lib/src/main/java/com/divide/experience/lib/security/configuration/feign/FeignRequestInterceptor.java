package com.divide.experience.lib.security.configuration.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
        Optional<String> o = getTokenValue();
        o.ifPresent(s -> template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, s)));
    }

    /**
     * Gets token value from jwt.
     *
     * @return token value
     */
    public Optional<String> getTokenValue() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
            return Optional.of(token.getToken().getTokenValue());
        }
        return Optional.empty();
    }
}
