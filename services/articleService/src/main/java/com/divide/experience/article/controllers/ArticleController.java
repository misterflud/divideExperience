package com.divide.experience.article.controllers;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.facades.ArticleFacade;
import com.divide.experience.article.objects.transport.MainArticleItem;
import com.divide.experience.article.objects.transport.UserArticleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AOleynikov on 02.01.2019.
 * /article.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class ArticleController {

    @Autowired
    private ArticleFacade articleFacade;

    /** Test. */
    @RequestMapping(value = "/something/",method = RequestMethod.GET)
    public ResponseEntity<String> checkServer() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("bom bom", HttpStatus.OK);
        System.out.println("bom bom");
        return responseEntity;
    }

    /** Test. */
    @RequestMapping(value = "/something2/",method = RequestMethod.GET)
    public String checkServer2() {
        return "greeting.html";
    }

    /** Test. */
    @RequestMapping(value = "/getJson", method = RequestMethod.GET, produces = "application/json")
    public String getJson(@RequestParam(value = "articleId", required = false) Integer articleId) {
        return "{\"id\": \"1\", \"title\": \"Hello\"}";
    }

    /**
     * Gets article.
     * @param articleId id of article.
     * @return dto of article.
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public MainArticleItem getArticle(@RequestParam("articleId") Integer articleId) {
        return articleFacade.getArticle(articleId);
    }

    /**
     * Adds article.
     * @param articleItem dto article.
     * @throws AddingArticleException business exception.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public void addArticle(@RequestBody UserArticleItem articleItem) throws AddingArticleException {
        articleFacade.addArticle(articleItem);
    }

    /**
     * Deletes article.
     * @param articleId id of article.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteArticle(@RequestParam("articleId") String articleId) {

    }
}
