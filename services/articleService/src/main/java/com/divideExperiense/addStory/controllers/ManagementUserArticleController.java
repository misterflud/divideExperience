package com.divideExperiense.addStory.controllers;

import com.divideExperiense.addStory.dataTransportObjects.MainArticleItem;
import com.divideExperiense.addStory.dataTransportObjects.UserArticleItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@RestController
public class ManagementUserArticleController {

    @RequestMapping(value = "/article/get", method = RequestMethod.GET, produces = "application/json")
    public void getArticle(@RequestParam("articleId") String articleId) {

    }
    /**
     * add, update
     */
    @RequestMapping(value = "/article/add", method = RequestMethod.POST, produces = "application/json")
    public void addArticle(@RequestParam("article") UserArticleItem articleItem) {

    }

    @RequestMapping(value = "/article/delete", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteArticle(@RequestParam("articleId") String articleId) {

    }
}
