package com.divide.experience.article.dao.services;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.objects.domain.ArticleModel;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>DAO for article.
 */
public interface ArticleDao {

    /**
     * Adds article to database.
     * @param articleModel model.
     */
    void addArticle(ArticleModel articleModel) throws AddingArticleException;

    /**
     * Updates article in database.
     * @param articleModel This is article.
     */
    void updateArticle(ArticleModel articleModel);

    /**
     * Gets article from database.
     * @param articleId This is id of article.
     * @return ArticleModel This returns after updating.
     */
    ArticleModel getArticle(Integer articleId);

    /**
     * Deletes article from database.
     * @param articleId This is article's id.
     */
    void deleteArticle(Integer articleId);
}
