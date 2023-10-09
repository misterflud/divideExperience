package com.divide.experience.article.security.filters;

import com.divide.experience.article.security.JwtTokenProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;


/**
 * Created by AOleynikov on 06.06.2019.
 */
@Slf4j
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String BASIC = "Basic";

    private JwtTokenProcessor tokenProcessor;

    /**
     * Constructor.
     */
    public LoginAuthenticationFilter(String url, AuthenticationManager authManager, JwtTokenProcessor jwtTokenProcessor) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.tokenProcessor = jwtTokenProcessor;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
        String headerValue = ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(request.getParameter("t"));
        if (headerValue == null || !headerValue.startsWith(BASIC) || headerValue.length() == AUTHORIZATION.length()) {
            log.error("No Basic token in request headers");
            throw new AuthenticationCredentialsNotFoundException("No Basic token in request headers");
        }
        headerValue = removeStart(headerValue, BASIC).trim();
        String[] loginPass = new String(Base64.getDecoder().decode(headerValue)).split(":");

        final Authentication auth = new UsernamePasswordAuthenticationToken(loginPass[0], loginPass[1]);
        return getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        String login = ((User) authResult.getPrincipal()).getUsername();
        String token = tokenProcessor.generateToken(login);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        response.setHeader(AUTHORIZATION, String.format("Bearer %s", token));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                              AuthenticationException failed) throws IOException, ServletException {
        log.error("Authentication failed");
        super.unsuccessfulAuthentication(req, res, failed);
    }
}
