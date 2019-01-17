package com.divideExperience.article.controllers;

import com.divideExperience.article.dataTransportObjects.AuthorItem;
import com.divideExperience.article.facades.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AOleynikov on 17.01.2019.
 */
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorFacade authorFacade;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addAuthor(@RequestBody AuthorItem authorItem) {
        authorFacade.addAuthor(authorItem);
    }

}
