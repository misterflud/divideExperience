package com.divide.experience.article.services;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.exceptions.NoSuchAuthorException;
import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.domain.ArticleModel;

import java.util.List;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>Service layer.
 */
public interface ArticleService {

    /**
     * Adds article.
     *
     * @param articleModel This is article.
     * @throws AddingArticleException business exception.
     */
    void addArticle(ArticleModel articleModel) throws AddingArticleException;


    /**
     * Saves article for future redaction (flag 'save' in dataBase will be false).
     *
     * @param articleModel article.
     */
    void saveArticle(ArticleModel articleModel);

    /**
     * Gets articles.
     *
     * @param pagination pagination.
     * @return articles list.
     */
    List<ArticleModel> getArticles(PaginationParameters pagination);

    /**
     * Gets article.
     *
     * @param articleId This is article id.
     * @return Article.
     */
    ArticleModel getArticle(Integer articleId);

    /**
     * Generates empty article, but with id.
     *
     * @return empty article with id.
     */
    ArticleModel generateNewArticle() throws NoSuchAuthorException;
}
