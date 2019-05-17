package com.divide.experience.article.services;

import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.objects.domain.ArticleModel;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>Service layer.
 */
public interface ArticleService {

    /**
     * Adds article.
     * @param articleModel This is article.
     * @throws AddingArticleException business exception.
     */
    void addArticle(ArticleModel articleModel) throws AddingArticleException;

    /**
     * Gets article.
     * @param articleId This is article's id.
     * @return Article.
     */
    ArticleModel getArticle(Integer articleId);
}
