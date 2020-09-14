package com.divide.experience.auth.facades.impl;

import com.divide.experience.auth.converters.UserAuthDetailsConverter;
import com.divide.experience.auth.facades.UserFacade;
import com.divide.experience.auth.objects.transport.UserItem;
import com.divide.experience.auth.services.UserService;
import com.divide.experience.lib.api.feign.auth.UserAuthDetailsData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 30.05.2019.
 */
@Component
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private UserAuthDetailsConverter converter;


    @Override
    public UserItem getUserByEmail(String email) {
        userService.getUserByEmail(email);
        return null;
    }

    @Override
    public UserAuthDetailsData getUserAuthDetails(String token) {
        return converter.convert(userService.getUserDetailsByAuthToken(token));
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setConverter(UserAuthDetailsConverter converter) {
        this.converter = converter;
    }
}
