package com.divide.experience.article.services;


import com.divide.experience.article.objects.domain.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by AOleynikov on 28.05.2019.
 */
public interface UserService {

    UserModel getUserByEmail(String email);

    UserDetails getUserDetailsByAuthToken(String authToken);
}
