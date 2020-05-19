package com.divide.experience.article.facades;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.transport.ArticleItem;

import java.util.List;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface ArticleFacade {

    /**
     * Adds a article.
     *
     * @param articleItem This is dto of article.
     * @throws AddingArticleException This is business exception.
     */
    void addArticle(ArticleItem articleItem) throws AddingArticleException;

    /**
     * Saves a article for future redaction (flag 'save' in dataBase will be false).
     *
     * @param articleItem article.
     */
    void saveArticle(ArticleItem articleItem);

    /**
     * Gets articles.
     *
     * @param pagination pagination.
     * @return list of article.
     */
    List<ArticleItem> getArticles(PaginationParameters pagination);

    /**
     * Gets a article.
     *
     * @param articleId This is id of article.
     * @return DTO of article.
     */
    ArticleItem getArticle(Integer articleId);

    /**
     * Deletes a article.
     *
     * @param articleId This is id of article.
     */
    void deleteArticle(Integer articleId);

    /**
     * Does all things for new article (when it doesn't save by author).
     *
     * @return article with id.
     */
    ArticleItem generateAllForArticle();
}
