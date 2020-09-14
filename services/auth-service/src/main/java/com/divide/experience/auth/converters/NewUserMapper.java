package com.divide.experience.auth.converters;

import com.divide.experience.auth.objects.Role;
import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.objects.transport.NewUserItem;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public NewUserMapper() {
        super(UserModel.class, NewUserItem.class);
    }
}
