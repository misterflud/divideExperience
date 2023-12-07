package com.divide.experience.article.converters;

import com.divide.experience.article.objects.domain.Role;
import com.divide.experience.article.objects.domain.UserModel;
import com.divide.experience.article.objects.transport.NewUserItem;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 29.05.2019.
 */
@Component
public class NewUserMapper extends AbstractMapper<UserModel, NewUserItem> {

    @Override
    void mapSpecificFields(NewUserItem source, UserModel destination) {
        destination.setRole(Role.valueOf(source.getRole()));
    }

    public NewUserMapper() {
        super(UserModel.class, NewUserItem.class);
    }
}
