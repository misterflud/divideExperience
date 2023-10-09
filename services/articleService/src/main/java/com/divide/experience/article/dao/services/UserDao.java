package com.divide.experience.article.dao.services;

import com.divide.experience.article.objects.domain.UserModel;

/**
 * Created by AOleynikov on 21.05.2019.
 */
public interface UserDao {

    /**
     * Gets by email which mention in registration.
     *
     * @param email email (login).
     * @return model.
     */
    UserModel getUserByEmail(String email);

    /**
     * Gets user bu nickname.
     *
     * @param nickName nick name.
     * @return model.
     */
    UserModel getUserByNickName(String nickName);

    /**
     * Don't use it, because current realization token generation doesn't save token in DB.
     *
     * @param authToken token.
     * @return model.
     */
    @Deprecated
    UserModel getUserByAuthToken(String authToken);

    /**
     * Adds new user.
     *
     * @param userModel model.
     */
    void addUser(UserModel userModel);

    /**
     * Updates information about user.
     *
     * @param userModel model.
     */
    void updateUser(UserModel userModel);
}
