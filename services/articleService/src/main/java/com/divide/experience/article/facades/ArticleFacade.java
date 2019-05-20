package com.divide.experience.article.facades;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.objects.transport.MainArticleItem;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface ArticleFacade {

    /**
     * Adds article.
     * @param articleItem This is dto of article.
     * @throws AddingArticleException This is business exception.
     */
    void addArticle(MainArticleItem articleItem) throws AddingArticleException;

    /**
     * Gets article.
     * @param articleId This is id of article.
     * @return DTO of article.
     */
    MainArticleItem getArticle(Integer articleId);

    /**
     * Deletes article.
     * @param articleId This is id of article.
     */
    void deleteArticle(Integer articleId);
}
