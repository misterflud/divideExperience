package com.divide.experience.auth.controllers;

import com.divide.experience.auth.facades.UserFacade;
import com.divide.experience.auth.objects.transport.UserAuthDetails;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 21.05.2019.
 */
@RestController
public class AuthorisationController {

    private UserFacade userFacade;

    /**
     * Validations token in filter.
     * Adds container for response.
     *
     * @return result
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    public String checkToken() {
        return "Token is correct";
    }

    /**
     * Provider auth information for others services.
     *
     * @param token for authentication and authorization.
     * @return information about user (roles, name, etc).
     */
    @RequestMapping(value = "/user_details", method = RequestMethod.GET)
    public UserAuthDetails getUserByToken(@RequestHeader String token) {
        return userFacade.getUserAuthDetails(token);
    }

    @Resource
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
}
