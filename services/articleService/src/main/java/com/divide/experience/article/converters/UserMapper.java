package com.divide.experience.article.converters;

import com.divide.experience.article.objects.domain.UserModel;
import com.divide.experience.article.objects.transport.UserItem;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 29.05.2019.
 */
@Component
public class UserMapper extends AbstractMapper<UserModel, UserItem> {

    public UserMapper() {
        super(UserModel.class, UserItem.class);
    }
}
