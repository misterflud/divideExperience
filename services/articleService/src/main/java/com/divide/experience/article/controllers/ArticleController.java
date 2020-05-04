package com.divide.experience.article.controllers;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.facades.ArticleFacade;
import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.transport.ArticleItem;
import com.divide.experience.article.objects.transport.UserArticleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@RestController
public class ArticleController {

    private ArticleFacade articleFacade;

    /**
     * Gets article.
     *
     * @return dto of article.
     */
    @GetMapping(value = "/all", produces = "application/json")
    public List<ArticleItem> getArticles(@RequestParam int pageSize,
                                         @RequestParam int currentPage) {
        PaginationParameters pageable = new PaginationParameters();
        pageable.currentPage = currentPage;
        pageable.pageSize = pageSize;
        return articleFacade.getArticles(pageable);
    }

    /**
     * Gets article.
     *
     * @param articleId id of article.
     * @return dto of article.
     */
    @GetMapping(value = "/get", produces = "application/json")
    public ArticleItem getArticle(@RequestParam("articleId") Integer articleId) {
        return articleFacade.getArticle(articleId);
    }

    /**
     * Adds article.
     *
     * @param articleItem dto article.
     * @throws AddingArticleException business exception.
     */
    @PostMapping(value = "/p/add", produces = "application/json")
    public void addArticle(@RequestBody UserArticleItem articleItem) throws AddingArticleException {
        articleFacade.addArticle(articleItem);
    }

    /**
     * Save article for feature changing.
     *
     * @param articleItem dto article.
     * @throws AddingArticleException business exception.
     */
    @PostMapping(value = "/p/save", produces = "application/json")
    public void saveArticle(@RequestBody UserArticleItem articleItem) throws AddingArticleException {
        articleFacade.saveArticle(articleItem);
    }

    /**
     * Create empty article.
     *
     * @return empty article with Id.
     */
    @GetMapping(value = "/p/write_article", produces = "application/json")
    public ArticleItem writeArticle() {
        return articleFacade.generateAllForArticle();
    }

    /**
     * Deletes article.
     *
     * @param articleId id of article.
     */
    @DeleteMapping(value = "/p/delete", produces = "application/json")
    public void deleteArticle(@RequestParam("articleId") String articleId) {

    }

    @Autowired
    public void setArticleFacade(ArticleFacade articleFacade) {
        this.articleFacade = articleFacade;
    }
}
