package com.divideExperience.article.daoServices;

import com.divideExperience.article.domainObjects.ArticleModel;
import com.divideExperience.article.exceptions.AddingArticleException;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface ArticleDao {

    void addArticle(ArticleModel articleModel) throws AddingArticleException;
    void updateArticle(ArticleModel articleModel);
    ArticleModel getArticle(Long articleId);
    void deleteArticle(Long articleId);
}
