package com.divide.experience.auth.services.impl;

import com.divide.experience.auth.dao.services.UserDao;
import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.services.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by AOleynikov on 28.05.2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public UserModel getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public UserModel getUserByAuthToken(String authToken) {
        return userDao.getUserByAuthToken(authToken);
    }

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
