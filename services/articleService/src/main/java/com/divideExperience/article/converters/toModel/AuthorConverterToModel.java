package com.divideExperience.article.converters.toModel;

import com.divideExperience.article.domainObjects.AuthorModel;
import com.divideExperience.article.dataTransportObjects.AuthorItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Component
public class AuthorConverterToModel implements Converter<AuthorItem, AuthorModel> {
    @Override
    public AuthorModel convert(AuthorItem authorItem) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setEmail(authorItem.getEmail());
        authorModel.setNickName(authorItem.getNickName());
        authorModel.setFirstName(authorItem.getFirstName());
        authorModel.setSecondName(authorItem.getSecondName());
        authorModel.setThirdName(authorItem.getThirdName());
        return authorModel;
    }
}
