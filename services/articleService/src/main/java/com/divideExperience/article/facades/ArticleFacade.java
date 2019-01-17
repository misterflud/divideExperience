package com.divideExperience.article.facades;

import com.divideExperience.article.dataTransportObjects.MainArticleItem;
import com.divideExperience.article.exceptions.AddingArticleException;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface ArticleFacade {
    void addArticle(MainArticleItem articleItem) throws AddingArticleException;
    MainArticleItem getArticle(Long articleId);
    void deleteArticle(Long articleId);
}
