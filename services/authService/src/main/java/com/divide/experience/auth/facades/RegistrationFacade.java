package com.divide.experience.auth.facades;

import com.divide.experience.auth.objects.transport.NewUserItem;

/**
 * Created by AOleynikov on 27.05.2019.
 */
public interface RegistrationFacade {

    /**
     * Adds new user.
     *
     * @param newUserItem dto.
     */
    void registrationUser(NewUserItem newUserItem);
}
