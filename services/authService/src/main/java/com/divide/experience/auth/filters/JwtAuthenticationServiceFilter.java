package com.divide.experience.auth.filters;

import com.divide.experience.auth.security.JwtAuthenticationToken;
import com.divide.experience.auth.security.TypeClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class JwtAuthenticationServiceFilter extends AbstractAuthenticationProcessingFilter {

    private static final String BEARER = "Bearer";

    public JwtAuthenticationServiceFilter(final String matcher, AuthenticationManager authenticationManager) {
        super(matcher);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) { //если мы уже аутентифицированы мы ничего не делаем
            return SecurityContextHolder.getContext().getAuthentication();
        }
        String headerValue = ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(request.getParameter("t"));
        if (headerValue == null) {
            throw new AuthenticationCredentialsNotFoundException("No JWT token in request headers");
        }
        JwtAuthenticationToken token =
                new JwtAuthenticationToken(removeStart(request.getHeader(AUTHORIZATION), BEARER), TypeClient.SERVICE);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
