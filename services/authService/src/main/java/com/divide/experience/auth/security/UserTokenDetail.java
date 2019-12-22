package com.divide.experience.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Anton Oleynikov {@literal <aoleynikov@fil-it.ru>}
 */
public class UserTokenDetail extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1396350063252302640L;

    private String login;

    public UserTokenDetail(UserDetails userDetails) {
        super(new ArrayList<>());
        this.login = userDetails.getUsername();
        setAuthenticated(true);
        setDetails(userDetails);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public UserTokenDetail(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
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
