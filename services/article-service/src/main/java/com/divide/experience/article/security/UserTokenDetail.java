package com.divide.experience.article.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class UserTokenDetail extends AbstractAuthenticationToken {

    private String login;

    /**
     * Basic constructor.
     *
     * @param userDetails userDetails.
     */
    public UserTokenDetail(UserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.login = userDetails.getUsername();
        setAuthenticated(true);
        setDetails(userDetails);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }
}
