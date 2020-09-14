package com.divide.experience.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class UserTokenDetails extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1091499794714971352L;

    private UserDetails userDetails;

    /**
     * This is a constructor for configuring spring user detail.
     *
     * @param userDetails user detail.
     */
    public UserTokenDetails(UserDetails userDetails) {
        super(new ArrayList<>());
        this.userDetails = userDetails;
        setAuthenticated(true);
        setDetails(userDetails);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public UserTokenDetails(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userDetails.getUsername();
    }
}
