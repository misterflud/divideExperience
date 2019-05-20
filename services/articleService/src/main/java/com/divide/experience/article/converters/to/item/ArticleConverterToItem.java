package com.divide.experience.article.converters.to.item;

import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.transport.MainArticleItem;
import com.divide.experience.article.objects.transport.UserArticleItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Component
public class ArticleConverterToItem implements Converter<ArticleModel, MainArticleItem> {

    /** Converts to item. */
    @Override
    public MainArticleItem convert(ArticleModel articleModel) {
        MainArticleItem articleItem = new UserArticleItem();
        articleItem.setBody(articleModel.getBody());
        articleItem.setTitle(articleModel.getTitle());
        articleItem.setId(articleModel.getId());
        return articleItem;
    }
}
