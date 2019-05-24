package com.divide.experience.article.converters.to.model;

import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.transport.MainArticleItem;
import com.divide.experience.article.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@Component
public class ArticleConverterToModel implements Converter<MainArticleItem, ArticleModel> {

    @Autowired
    private AuthorConverterToModel authorConverterToModel;

    @Autowired
    private AuthorService authorService;

    /** Converts to model. */
    @Override
    public ArticleModel convert(MainArticleItem userArticleItem) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setBody(userArticleItem.getBody());
        articleModel.setTitle(userArticleItem.getTitle());
        articleModel.setAuthorModel(authorService.getAuthorByEmail(userArticleItem.getAuthorItem().getEmail()));
        return articleModel;
    }
}