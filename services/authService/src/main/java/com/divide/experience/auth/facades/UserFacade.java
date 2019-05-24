package com.divide.experience.auth.facades;

import com.divide.experience.auth.objects.transport.UserItem;

/**
 * Created by AOleynikov on 27.05.2019.
 */
public interface UserFacade {

    UserItem getUserByEmail(String email);
}
