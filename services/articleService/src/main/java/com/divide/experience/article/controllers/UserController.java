package com.divide.experience.article.controllers;

import com.divide.experience.article.facades.RegistrationFacade;
import com.divide.experience.article.facades.UserFacade;
import com.divide.experience.article.objects.transport.NewUserItem;
import com.divide.experience.article.objects.transport.UserItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 21.05.2019.
 * Adds new user.
 */
@RestController
@RequestMapping("/auth")
@Api(value = "Process operations with user in a public access.",
        tags = "UserController")
public class UserController {

    private RegistrationFacade registrationFacade;

    private UserFacade userFacade;

    /**
     * Registration.
     *
     * @param newUserItem dto.
     * @return result, in future adds container like Result.
     */
    @ApiOperation(value = "Registers new user")
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public boolean registrationUser(@RequestBody NewUserItem newUserItem) {
        registrationFacade.registrationUser(newUserItem);
        return true;
    }

    /**
     * Gets user by email.
     *
     * @param email email.
     * @return dto.
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public UserItem getUser(String email) {
        return userFacade.getUserByEmail(email);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login() {
        return ResponseEntity.ok().build();
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
