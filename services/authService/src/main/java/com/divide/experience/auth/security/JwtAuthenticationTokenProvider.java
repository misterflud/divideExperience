package com.divide.experience.auth.security;

import com.divide.experience.auth.services.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Service
public class JwtAuthenticationTokenProvider implements AuthenticationProvider {

    private JwtTokenProcessor tokenProcessor;
    private UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationTokenProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "Authentication is missing");
        Assert.isInstanceOf(JwtAuthenticationToken.class, authentication, "This method only accepts JwtAuthenticationToken");
        if (((JwtAuthenticationToken) authentication).getTypeClient().equals(TypeClient.USER)) {
            String email = tokenProcessor.getUserFromToken(authentication.getName());
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if (userDetails != null) {
                return new UserTokenDetail(userDetails);
            }
        } else {
            UserDetails userDetails = tokenProcessor.getUserDetailsForService(authentication.getName());
            if (userDetails != null) {
                return new UserTokenDetail(userDetails);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }

    @Resource
    public void setTokenProcessor(JwtTokenProcessor tokenProcessor) {
        this.tokenProcessor = tokenProcessor;
    }

    @Resource
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
