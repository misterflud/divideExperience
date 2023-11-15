package com.divide.experience.article.dao.services;

import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.domain.UserModel;

import java.util.List;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>DAO for article.
 */
public interface ArticleDao {

    /**
     * Adds article to database.
     *
     * @param articleModel model.
     */
    ArticleModel addArticle(ArticleModel articleModel);

    /**
     * Updates article in database.
     *
     * @param articleModel This is article.
     */
    void updateArticle(ArticleModel articleModel);

    /**
     * Gets articles from database.
     *
     * @return ArticleModel This returns after updating.
     */
    List<ArticleModel> getArticles(PaginationParameters pagination);

    /**
     * Gets article from database.
     *
     * @param articleId This is id of article.
     * @return ArticleModel This returns after updating.
     */
    ArticleModel getArticle(Integer articleId);

    /**
     * Deletes article from database.
     *
     * @param articleId This is article id.
     */
    void deleteArticle(Integer articleId);

    /**
     * Gets not saved article.
     *
     * @param userModel article.
     * @return article.
     */
    ArticleModel getNotSavedArticle(UserModel userModel);
}
