package com.divide.experience.auth.converters;

import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.objects.domain.UserRole;
import com.divide.experience.auth.objects.transport.NewUserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by AOleynikov on 29.05.2019.
 */
@Component
public class NewUserMapper extends AbstractMapper<UserModel, NewUserItem> {

    @Override
    void mapSpecificFields(NewUserItem source, UserModel destination) {
        Set<UserRole> roles = new HashSet<>();
        destination.setUserRoles(roles);
    }

    @Autowired
    public NewUserMapper() {
        super(UserModel.class, NewUserItem.class);
    }
}
