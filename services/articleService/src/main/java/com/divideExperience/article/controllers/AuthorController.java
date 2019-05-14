package com.divideExperience.article.controllers;

import com.divideExperience.article.dataTransportObjects.AuthorItem;
import com.divideExperience.article.facades.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by AOleynikov on 17.01.2019.
 */
@RestController
@RequestMapping("/author")
public class AuthorController {
//    http://localhost:6002/article/author/add
    @Autowired
    private AuthorFacade authorFacade;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public void addAuthor(@RequestBody AuthorItem authorItem) {
        authorFacade.addAuthor(authorItem);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public AuthorItem getAuthor(@RequestParam Integer authorId) {
        return authorFacade.getAuthor(authorId);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteAuthor(@RequestBody AuthorItem authorItem) {
        authorFacade.deleteAuthor(authorItem);
    }


}
