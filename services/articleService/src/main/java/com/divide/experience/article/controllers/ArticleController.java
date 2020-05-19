package com.divide.experience.article.controllers;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.facades.ArticleFacade;
import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.transport.ArticleItem;
import com.divide.experience.article.objects.transport.UserArticleItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
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
@Api(value = "Contains operations with articles.", tags = "ArticleController")
public class ArticleController {

    private ArticleFacade articleFacade;

    @ApiOperation(value = "Pagination articles for the main page.",
            notes = "In future, 'pageSize' and 'currentPage' will change on a standard object for pagination.",
            response = ArticleItem.class,
            responseContainer = "List")
    @GetMapping(value = "/all", produces = "application/json")
    public List<ArticleItem> getArticles(@ApiParam(value = "Page size", allowableValues = "range[1, infinity]", required = true)
                                         @RequestParam int pageSize,
                                         @ApiParam(value = "The number of the current page", allowableValues = "range[0, infinity]",
                                                 required = true)
                                         @RequestParam int currentPage) {
        PaginationParameters pageable = new PaginationParameters();
        pageable.currentPage = currentPage;
        pageable.pageSize = pageSize;
        return articleFacade.getArticles(pageable);
    }

    @ApiOperation(value = "Gets a article by id.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Gets the article"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "The Article not found")})
    @GetMapping(value = "/get", produces = "application/json")
    public ArticleItem getArticle(@ApiParam(value = "The Id of the article", allowableValues = "range[0, infinity]", required = true)
                                  @RequestParam("articleId") Integer articleId) {
        return articleFacade.getArticle(articleId);
    }

    @ApiOperation(value = "Adds an article to server.",
            notes = "Adds an article. The article will be stored permanently (The article can be deleted or changed only by an admin).",
            authorizations = {
                    @Authorization(value = "JWT token", scopes = {
                            @AuthorizationScope(scope = "admin", description = "Allows deletes an article.")})
            },
            tags = "protect_resource")
    @PostMapping(value = "/p/add", produces = "application/json")
    public void addArticle(
            @ApiParam(value = "The article for adding from text editor.", required = true)
                           @RequestBody UserArticleItem articleItem) throws AddingArticleException {
        articleFacade.addArticle(articleItem);
    }

    @ApiOperation(value = "Saves an article for feature changing.",
            notes = "Saves between a session for example. You can have only one unsaved article",
            tags = "protect_resource")
    @PostMapping(value = "/p/save", produces = "application/json")
    public void saveArticle(@ApiParam(value = "Saves an article for future changing.", required = true)
                            @RequestBody UserArticleItem articleItem) throws AddingArticleException {
        articleFacade.saveArticle(articleItem);
    }

    @ApiOperation(value = "Gets a new empty article (with id).", tags = "protect_resource")
    @GetMapping(value = "/p/write_article", produces = "application/json")
    public ArticleItem writeArticle() {
        return articleFacade.generateAllForArticle();
    }

    @ApiOperation(value = "Deletes an article.",
            authorizations = {
                    @Authorization(value = "JWT token", scopes = {
                            @AuthorizationScope(scope = "admin", description = "Allows deletes an article.")})
            },
            tags = {"protect_resource", "in_process"})
    @DeleteMapping(value = "/p/delete", produces = "application/json")
    public void deleteArticle(@ApiParam(value = "Deletes an article.", allowableValues = "range[0, infinity]", required = true)
                              @RequestParam("articleId") String articleId) {

    }

    @Autowired
    public void setArticleFacade(ArticleFacade articleFacade) {
        this.articleFacade = articleFacade;
    }
}
