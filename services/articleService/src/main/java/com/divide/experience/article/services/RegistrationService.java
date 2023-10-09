package com.divide.experience.article.services;

import com.divide.experience.article.objects.domain.UserModel;

/**
 * Created by AOleynikov on 21.05.2019.
 */
public interface RegistrationService {

    /**
     * Adds new user.
     *
     * @param userModel userModel.
     * @param password user's password.
     */
    void registrationUser(UserModel userModel, String password);
}
