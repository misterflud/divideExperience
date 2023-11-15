package com.divide.experience.article.converters.to.model;

import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.transport.ArticleItem;
import com.divide.experience.article.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@Component
public class ArticleConverterToModel implements Converter<ArticleItem, ArticleModel> {

    private UserService userService;

    /** Converts to model. */
    @Override
    public ArticleModel convert(ArticleItem userArticleItem) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setBody(userArticleItem.getBody());
        articleModel.setTitle(userArticleItem.getTitle());
        articleModel.setId(userArticleItem.getId());
        articleModel.setUserModel(userService.getUserByEmail(userArticleItem.getAuthorItem().getEmail()));
        return articleModel;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
