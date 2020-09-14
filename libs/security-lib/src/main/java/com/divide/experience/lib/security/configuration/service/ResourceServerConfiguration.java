package com.divide.experience.lib.security.configuration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@SuppressWarnings({"checkstyle:multiplestringliterals", "JavadocMethod", "JavadocTagContinuationIndentation",
    "UnnecessaryParentheses", "ArrayTrailingComma"})
@EnableFeignClients("com.divide.experience.lib.security.configuration")
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] SECURED_MATCHERS = new String[] {
        "**/p/**"
    };

    private static final String[] OPEN_MATCHERS = new String[] {
        "**/o/**"
    };

    private static final String[] SWAGGER_MATCHERS = new String[] {
        "/v2/api-docs",
        "/api-docs",
        "/configuration/**",
        "/swagger-ui.html",
        "/webjars/**",
        "/swagger-resources/**",
    };

    private static final String[] ACTUATOR_MATCHERS = new String[]{
        "/acutator/**",
    };

    @Value("${security.oauth2.back_services.jwt.key-value}")
    private String secret;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests((authorizeRequests) ->
                authorizeRequests
                    .mvcMatchers(SECURED_MATCHERS)
                    .authenticated()
                    .mvcMatchers(SWAGGER_MATCHERS)
                    .permitAll()
                    .mvcMatchers(OPEN_MATCHERS)
                    .permitAll()
                    .mvcMatchers(ACTUATOR_MATCHERS)
                    .permitAll()
                    .anyRequest()
                    .permitAll()

            )
            .csrf().disable()
            .oauth2ResourceServer((oauth2ResourceServer) ->
                oauth2ResourceServer.jwt((jwt) -> {
                    jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter());
                    jwt.decoder(jwtDecoder());
                })
            );
    }

    JwtDecoder jwtDecoder() {
        SecretKey secretKey = new SecretKey() {

            private static final long serialVersionUID = -8386890134667611694L;

            @Override
            public String getAlgorithm() {
                return "HS256";
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return secret.getBytes();
            }
        };
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }
}
