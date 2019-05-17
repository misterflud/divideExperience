package com.divide.experience.article.services.impl;

import com.divide.experience.article.dao.services.ArticleDao;
import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.services.ArticleService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
