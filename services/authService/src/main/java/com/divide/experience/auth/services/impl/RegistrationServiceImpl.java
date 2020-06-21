package com.divide.experience.auth.services.impl;

import com.divide.experience.auth.dao.services.UserDao;
import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.services.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 21.05.2019.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    @Override
    public void registrationUser(UserModel userModel, String password) {
        if (!checkExistingUser(userModel.getEmail())) {
            userModel.setPassword(passwordEncoder.encode(password));
            userDao.addUser(userModel);
        } else {
            //TODO: throw exception if user already exist (add handler exception)
        }
    }

    /**
     * Checks user's filed on uniqueness.
     *
     * @return boolean This is result of checks.
     */
    private boolean checkExistingUser(String email) {
        return userDao.getUserByEmail(email) != null;
    }

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Resource
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
