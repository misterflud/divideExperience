package com.divide.experience.auth.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AOleynikov on 21.05.2019.
 */
@RestController
public class AuthorisationController {


    @RequestMapping(value = "/check1", method = RequestMethod.GET)
    public String check(@AuthenticationPrincipal String tt) {
        System.out.println("check1");
        return "check1";
    }

    @RequestMapping(value = "/check2", method = RequestMethod.POST)
    public String check2() {
        System.out.println("check2");
        return "check2";
    }

    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    public String checkToken() {
        System.out.println("Token is correct");
        return "Token is correct";
    }
}
