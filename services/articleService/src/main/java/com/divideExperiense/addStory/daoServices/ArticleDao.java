package com.divideExperiense.addStory.daoServices;

import com.divideExperiense.addStory.domainObjects.ArticleModel;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface ArticleDao {

    void addArticle(ArticleModel articleModel);
    void updateArticle(ArticleModel articleModel);
    void getArticle(String articleId);
    void deleteArticle(String articleId);
}
