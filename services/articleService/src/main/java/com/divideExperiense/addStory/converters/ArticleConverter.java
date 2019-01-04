package com.divideExperiense.addStory.converters;

import com.divideExperiense.addStory.dataTransportObjects.UserArticleItem;
import com.divideExperiense.addStory.domainObjects.ArticleModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@Component
public class ArticleConverter implements Converter<UserArticleItem, ArticleModel> {
    @Override
    public ArticleModel convert(UserArticleItem userArticleItem) {
        return null;
    }
}
