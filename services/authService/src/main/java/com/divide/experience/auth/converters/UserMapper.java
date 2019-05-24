package com.divide.experience.auth.converters;

import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.objects.transport.UserItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 29.05.2019.
 */
@Component
public class UserMapper extends AbstractMapper<UserModel, UserItem> {

    @Autowired
    public UserMapper() {
        super(UserModel.class, UserItem.class);
    }
}
