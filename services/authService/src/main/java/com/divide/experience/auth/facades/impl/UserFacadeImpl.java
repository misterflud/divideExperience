package com.divide.experience.auth.facades.impl;

import com.divide.experience.auth.facades.UserFacade;
import com.divide.experience.auth.objects.transport.UserItem;
import com.divide.experience.auth.services.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 30.05.2019.
 */
@Component
public class UserFacadeImpl implements UserFacade {

    private UserService userService;



    @Override
    public UserItem getUserByEmail(String email) {
        userService.getUserByEmail(email);
        return null;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
