package com.divideExperience.article.controllers;

import com.divideExperience.article.dataTransportObjects.MainArticleItem;
import com.divideExperience.article.dataTransportObjects.UserArticleItem;
import com.divideExperience.article.exceptions.AddingArticleException;
import com.divideExperience.article.facades.ArticleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@RestController
//@RequestMapping("/get")
public class ArticleController {

    @Autowired
    private ArticleFacade articleFacade;
//   http://localhost:8083/article/rest/
    @RequestMapping(value = "/something/",method = RequestMethod.GET)
    public ResponseEntity<String> checkServer() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("bom bom", HttpStatus.OK);
        System.out.println("bom bom");
        return responseEntity;
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public MainArticleItem getArticle(@RequestParam("articleId") Long articleId) {
        return articleFacade.getArticle(articleId);
    }
    /**
     * add, update
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public void addArticle(@RequestBody UserArticleItem articleItem) throws AddingArticleException {
        articleFacade.addArticle(articleItem);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteArticle(@RequestParam("articleId") String articleId) {

    }
}
