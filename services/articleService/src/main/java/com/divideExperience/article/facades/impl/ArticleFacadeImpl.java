package com.divideExperience.article.facades.impl;

import com.divideExperience.article.converters.toItem.ArticleConverterToItem;
import com.divideExperience.article.converters.toModel.ArticleConverterToModel;
import com.divideExperience.article.exceptions.AddingArticleException;
import com.divideExperience.article.facades.ArticleFacade;
import com.divideExperience.article.services.ArticleService;
import com.divideExperience.article.dataTransportObjects.MainArticleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Component
public class ArticleFacadeImpl implements ArticleFacade {

    @Autowired
    private ArticleConverterToModel articleConverterToModel;

    @Autowired
    private ArticleConverterToItem articleConverterToItem;

    @Autowired
    private ArticleService articleService;

    @Override
    public void addArticle(MainArticleItem articleItem) throws AddingArticleException {
        articleService.addArticle(articleConverterToModel.convert(articleItem));
    }

    @Override
    public MainArticleItem getArticle(Integer articleId) {
        return articleConverterToItem.convert(articleService.getArticle(articleId));
    }

    @Override
    public void deleteArticle(Integer articleId) {

    }
}
