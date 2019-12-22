package com.divide.experience.auth.dao.services;

import com.divide.experience.auth.objects.domain.UserModel;

/**
 * Created by AOleynikov on 21.05.2019.
 */
public interface UserDao {

    UserModel getUserByEmail(String email);

    UserModel getUserByNickName(String nickName);

    UserModel getUserByAuthToken(String authToken);

    void addUser(UserModel userModel);

    void updateUser(UserModel userModel);
}
