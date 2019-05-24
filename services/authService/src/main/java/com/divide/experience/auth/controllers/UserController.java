package com.divide.experience.auth.controllers;

import com.divide.experience.auth.facades.RegistrationFacade;
import com.divide.experience.auth.facades.UserFacade;
import com.divide.experience.auth.objects.transport.NewUserItem;
import com.divide.experience.auth.objects.transport.UserItem;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AOleynikov on 21.05.2019.
 * Adds new user.
 */
@RestController
public class UserController {

    private RegistrationFacade registrationFacade;

    private UserFacade userFacade;

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public boolean registrationUser(@RequestBody NewUserItem newUserItem) {
        registrationFacade.registrationUser(newUserItem);
        return true;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public UserItem getUser(String email) {
        return userFacade.getUserByEmail(email);
    }

    @Resource
    public void setRegistrationFacade(RegistrationFacade registrationFacade) {
        this.registrationFacade = registrationFacade;
    }

    @Resource
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
}
