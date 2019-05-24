package com.divide.experience.auth.filters;

import com.divide.experience.auth.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;

/**
 * @author Anton Oleynikov {@literal <aoleynikov@fil-it.ru>}
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) { //если мы уже аутентифицированы мы ничего не делаем
            chain.doFilter(request, response);
            return;
        }
        String headerValue = ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(request.getParameter("t"));
        if (headerValue == null) {
            throw new AuthenticationCredentialsNotFoundException("No JWT token in request headers");
        }

        JwtAuthenticationToken token = new JwtAuthenticationToken(removeStart(request.getHeader(AUTHORIZATION), BEARER));
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
}
