package com.divide.experience.auth.facades;

import com.divide.experience.auth.objects.transport.UserAuthDetails;
import com.divide.experience.auth.objects.transport.UserItem;

/**
 * Created by AOleynikov on 27.05.2019.
 */
public interface UserFacade {

    /**
     * Provides base information about user.
     *
     * @param email email which mentions in authorization.
     * @return dto user.
     */
    UserItem getUserByEmail(String email);

    /**
     * Provides confident information about user
     *
     * @param token from token provider.
     * @return auth infromation about user.
     */
    UserAuthDetails getUserAuthDetails(String token);
}
