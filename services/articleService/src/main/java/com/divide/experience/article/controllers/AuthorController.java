package com.divide.experience.article.controllers;

import com.divide.experience.article.facades.AuthorFacade;
import com.divide.experience.article.objects.transport.AuthorItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by AOleynikov on 17.01.2019.
 */
@RestController
@Api(value = "Contains operations with authors.", tags = "AuthorController")
@RequestMapping("/author")
public class AuthorController {

    private AuthorFacade authorFacade;

    @ApiOperation(value = "Adds a new author.", tags = "protect_resource")
    @RequestMapping(value = "/p/add", method = RequestMethod.POST, produces = "application/json")
    public void addAuthor(@ApiParam(value = "The DTO of a new author.", required = true)
                          @RequestBody AuthorItem authorItem) {
        authorFacade.addAuthor(authorItem);
    }

    @ApiOperation(value = "Gets the author.")
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public AuthorItem getAuthor(@ApiParam(value = "The Id of the author.", allowableValues = "range[1, infinity]", required = true)
                                @RequestParam Integer authorId) {
        return authorFacade.getAuthor(authorId);
    }

    @ApiOperation(value = "Deletes the author.", tags = "protect_resource")
    @RequestMapping(value = "/p/delete", method = RequestMethod.POST, produces = "application/json")
    public void deleteAuthor(@ApiParam(value = "The DTO of the author.", required = true)
                             @RequestBody AuthorItem authorItem) {
        authorFacade.deleteAuthor(authorItem);
    }

    @Autowired
    public void setAuthorFacade(AuthorFacade authorFacade) {
        this.authorFacade = authorFacade;
    }
}
