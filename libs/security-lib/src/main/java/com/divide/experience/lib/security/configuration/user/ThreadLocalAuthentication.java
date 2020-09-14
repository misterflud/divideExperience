package com.divide.experience.lib.security.configuration.user;

import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 * <p>
 *     User from thread local storage.
 * </p>
 */
@ToString
public class ThreadLocalAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -4198606171639923075L;

    private final UserSecurityDetails userDetails;

    /**
     * Token storage user.
     *
     * @param userDetails auth user.
     */
    public ThreadLocalAuthentication(UserSecurityDetails userDetails) {
        super(new ArrayList<>());
        this.userDetails = userDetails;
        setAuthenticated(true);
        setDetails(userDetails);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userDetails.getId();
    }

    public UserSecurityDetails getUserDetails() {
        return userDetails;
    }
}
