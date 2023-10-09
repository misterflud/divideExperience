package com.divide.experience.article.facades.impl;

import com.divide.experience.article.converters.UserAuthDetailsConverter;
import com.divide.experience.article.facades.UserFacade;
import com.divide.experience.article.objects.transport.UserAuthDetails;
import com.divide.experience.article.objects.transport.UserItem;
import com.divide.experience.article.services.UserService;
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
    public UserAuthDetails getUserAuthDetails(String token) {
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
