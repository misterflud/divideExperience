package com.divide.experience.auth.controllers;

import com.divide.experience.auth.facades.UserFacade;
import com.divide.experience.auth.objects.transport.UserAuthDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 21.05.2019.
 */
@RestController
@Api(value = "It contains operations for authorization (including cross authorization services).",
        tags = "AuthorisationController")
public class AuthorisationController {

    private UserFacade userFacade;

    @ApiOperation(value = "Checks JWT token.",
            notes = "This endpoint was created for checking a token by JwtAuthenticationUserFilter. "
                    + "If you take this response then you have a correct JWT token.")
    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    public String checkToken() {
        return "Token is correct";
    }

    @ApiOperation(value = "Returns information about user (just for inner services).",
            notes = "In future, it will be moved from a public access to a feign client")
    @RequestMapping(value = "/user_details", method = RequestMethod.GET)
    public UserAuthDetails getUserByToken(@ApiParam(value = "JWT token", example = "Bearer asdad1213xffvx2311311a",
            required = true)
                                          @RequestHeader String token) {
        return userFacade.getUserAuthDetails(token);
    }

    @Resource
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
}
