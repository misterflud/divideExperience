package com.divideExperience.article.controllers;

import com.divideExperience.article.dataTransportObjects.AuthorItem;
import com.divideExperience.article.facades.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AuthorItem getAuthor(@RequestParam Long authorId) {
        return authorFacade.getAuthor(authorId);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteAuthor(@RequestBody AuthorItem authorItem) {
        authorFacade.deleteAuthor(authorItem);
    }


}
