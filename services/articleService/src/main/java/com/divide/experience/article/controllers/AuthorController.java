package com.divide.experience.article.controllers;

import com.divide.experience.article.facades.AuthorFacade;
import com.divide.experience.article.objects.transport.AuthorItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by AOleynikov on 17.01.2019.
 *
 */
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorFacade authorFacade;

    /**
     * Adds author.
     * @param authorItem dto of author.
     */
    @RequestMapping(value = "/p/add", method = RequestMethod.POST, produces = "application/json")
    public void addAuthor(@RequestBody AuthorItem authorItem) {
        authorFacade.addAuthor(authorItem);
    }

    /**
     * Gets author.
     * @param authorId id of author.
     * @return dto of author.
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public AuthorItem getAuthor(@RequestParam Integer authorId) {
        return authorFacade.getAuthor(authorId);
    }

    /**
     * Deletes author.
     * @param authorItem dto of author.
     */
    @RequestMapping(value = "/p/delete", method = RequestMethod.POST)
    public void deleteAuthor(@RequestBody AuthorItem authorItem) {
        authorFacade.deleteAuthor(authorItem);
    }


}
