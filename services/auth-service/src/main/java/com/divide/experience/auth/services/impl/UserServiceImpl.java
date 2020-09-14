package com.divide.experience.auth.services.impl;

import com.divide.experience.auth.dao.services.UserDao;
import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.security.JwtTokenProcessor;
import com.divide.experience.auth.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 28.05.2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private JwtTokenProcessor tokenProcessor;
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public UserModel getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public UserDetails getUserDetailsByAuthToken(String authToken) {
        String email = tokenProcessor.getUserFromToken(authToken);
        return userDetailsService.loadUserByUsernameWithoutPassword(email);
    }

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Resource
    public void setTokenProcessor(JwtTokenProcessor tokenProcessor) {
        this.tokenProcessor = tokenProcessor;
    }

    @Resource
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
