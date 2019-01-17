package com.divideExperience.article.services;

import com.divideExperience.article.domainObjects.ArticleModel;
import com.divideExperience.article.dataTransportObjects.MainArticleItem;
import com.divideExperience.article.exceptions.AddingArticleException;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface ArticleService {
    void addArticle(ArticleModel articleModel) throws AddingArticleException;
    MainArticleItem getArticle(Long articleId);
}
