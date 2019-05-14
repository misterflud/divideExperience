package com.divideExperience.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AOleynikov on 04.03.2019.
 */

@Controller
public class HomePageController {

    @RequestMapping("/")
    public String getHomePage() {
        return "index";
    }
}
