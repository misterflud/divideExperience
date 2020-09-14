package com.divide.experience.article.facades.impl;

import com.divide.experience.article.converters.to.item.ArticleConverterToItem;
import com.divide.experience.article.converters.to.model.ArticleConverterToModel;
import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.exceptions.NoSuchAuthorException;
import com.divide.experience.article.facades.ArticleFacade;
import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.transport.ArticleItem;
import com.divide.experience.article.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Component
public class ArticleFacadeImpl implements ArticleFacade {

    private ArticleConverterToModel articleConverterToModel;
    private ArticleConverterToItem articleConverterToItem;
    private ArticleService articleService;

    @Override
    public void addArticle(ArticleItem articleItem) throws AddingArticleException {
        articleService.addArticle(articleConverterToModel.convert(articleItem));
    }

    @Override
    public void saveArticle(ArticleItem articleItem) {
        articleService.saveArticle(articleConverterToModel.convert(articleItem));
    }

    @Override
    public List<ArticleItem> getArticles(PaginationParameters pagination) {
        List<ArticleItem> list = new ArrayList<>(pagination.pageSize);
        articleService.getArticles(pagination).forEach(a -> list.add(articleConverterToItem.convert(a)));
        return list;
    }

    @Override
    public ArticleItem getArticle(Integer articleId) {
        return articleConverterToItem.convert(articleService.getArticle(articleId));
    }

    @Override
    public void deleteArticle(Integer articleId) {

    }

    @Override
    public ArticleItem generateAllForArticle() throws NoSuchAuthorException {
        return articleConverterToItem.convert(articleService.generateNewArticle());
    }

    @Autowired
    public void setArticleConverterToModel(ArticleConverterToModel articleConverterToModel) {
        this.articleConverterToModel = articleConverterToModel;
    }

    @Autowired
    public void setArticleConverterToItem(ArticleConverterToItem articleConverterToItem) {
        this.articleConverterToItem = articleConverterToItem;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
