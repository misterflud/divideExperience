package com.divideExperience.article.converters.toModel;

import com.divideExperience.article.dataTransportObjects.MainArticleItem;
import com.divideExperience.article.domainObjects.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@Component
public class ArticleConverterToModel implements Converter<MainArticleItem, ArticleModel> {
    @Autowired
    private AuthorConverterToModel authorConverterToModel;
    @Override
    public ArticleModel convert(MainArticleItem userArticleItem) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setDate(new Date());
        articleModel.setBody(userArticleItem.getBody());
        articleModel.setTitle(userArticleItem.getTitle());
        articleModel.setAuthorModel(authorConverterToModel.convert(userArticleItem.getAuthorItem()));
        return articleModel;
    }
}
