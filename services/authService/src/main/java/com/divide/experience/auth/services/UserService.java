package com.divide.experience.auth.services;

import com.divide.experience.auth.objects.domain.UserModel;

/**
 * Created by AOleynikov on 28.05.2019.
 */
public interface UserService {

    UserModel getUserByEmail(String email);

    UserModel getUserByAuthToken(String authToken);
}
