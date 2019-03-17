package com.divideExperience.article.services.Impl;

import com.divideExperience.article.converters.toItem.ArticleConverterToItem;
import com.divideExperience.article.daoServices.ArticleDao;
import com.divideExperience.article.dataTransportObjects.MainArticleItem;
import com.divideExperience.article.domainObjects.ArticleModel;
import com.divideExperience.article.exceptions.AddingArticleException;
import com.divideExperience.article.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by AOleynikov on 04.01.2019.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;


    @Override
    public void addArticle(ArticleModel articleModel) throws AddingArticleException {
        articleModel.setDate(new Date());
        articleDao.addArticle(articleModel);
    }

    @Override
    public ArticleModel getArticle(Integer articleId) {
        return articleDao.getArticle(articleId);
    }
}
