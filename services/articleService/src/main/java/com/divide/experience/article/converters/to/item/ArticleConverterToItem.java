package com.divide.experience.article.converters.to.item;

import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.transport.ArticleItem;
import com.divide.experience.article.objects.transport.UserArticleItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Component
public class ArticleConverterToItem implements Converter<ArticleModel, ArticleItem> {
    private static final String pattern = "dd.MM.yyyy HH:mm";
    /** Converts to item. */
    @Override
    public ArticleItem convert(ArticleModel articleModel) {
        ArticleItem articleItem = new UserArticleItem();
        articleItem.setBody(articleModel.getBody());
        Format formatter = new SimpleDateFormat(pattern);
        articleItem.setDate(formatter.format(articleModel.getDate()));
        articleItem.setTitle(articleModel.getTitle());
        articleItem.setId(articleModel.getId());
//        articleItem.setAuthorItem(articleModel.getAuthorModel());
        return articleItem;
    }
}
