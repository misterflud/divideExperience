package com.divide.experience.article.facades.impl;

import com.divide.experience.article.converters.to.item.ArticleConverterToItem;
import com.divide.experience.article.converters.to.model.ArticleConverterToModel;
import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.facades.ArticleFacade;
import com.divide.experience.article.objects.transport.MainArticleItem;
import com.divide.experience.article.services.ArticleService;
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

    /**
     * Adds article.
     * @param articleItem This is dto of article.
     * @throws AddingArticleException This is business exception.
     */
    @Override
    public void addArticle(MainArticleItem articleItem) throws AddingArticleException {
        articleService.addArticle(articleConverterToModel.convert(articleItem));
    }

    /**
     * Gets article.
     * @param articleId This is id of article.
     * @return DTO of article.
     */
    @Override
    public MainArticleItem getArticle(Integer articleId) {
        return articleConverterToItem.convert(articleService.getArticle(articleId));
    }

    /**
     * Deletes article.
     * @param articleId This is id of article.
     */
    @Override
    public void deleteArticle(Integer articleId) {

    }
}
