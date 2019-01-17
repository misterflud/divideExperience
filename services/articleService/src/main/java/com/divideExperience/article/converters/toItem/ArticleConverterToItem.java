package com.divideExperience.article.converters.toItem;

import com.divideExperience.article.dataTransportObjects.MainArticleItem;
import com.divideExperience.article.dataTransportObjects.UserArticleItem;
import com.divideExperience.article.domainObjects.ArticleModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Component
public class ArticleConverterToItem implements Converter<ArticleModel, MainArticleItem> {
    @Override
    public MainArticleItem convert(ArticleModel articleModel) {

        MainArticleItem articleItem = new UserArticleItem();
        articleItem.setBody(articleModel.getBody());
        articleItem.setTitle(articleModel.getTitle());
        articleItem.setId(articleModel.getId());
        return articleItem;
    }
}
