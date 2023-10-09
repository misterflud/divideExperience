package com.divide.experience.article.security.service;

import com.divide.experience.article.objects.domain.UserModel;
import com.divide.experience.article.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;

/**
 * Created by AOleynikov on 04.06.2019.
 */
@Service
public class UserDetailsServiceImpl implements CustomUserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userService.getUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
            userModel.getEmail(),
            userModel.getPassword(),
            getRoles(userModel)
        );
    }

    @Override
    public UserDetails loadUserByUsernameWithoutPassword(String email) throws UsernameNotFoundException {
        UserModel userModel = userService.getUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
            userModel.getEmail(),
            "",
            getRoles(userModel)
        );
    }

    private Set<GrantedAuthority> getRoles(UserModel userModel) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userModel.getRole().name()));
        return roles;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
