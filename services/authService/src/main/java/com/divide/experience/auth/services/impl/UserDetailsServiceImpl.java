package com.divide.experience.auth.services.impl;

import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.services.CustomUserDetailsService;
import com.divide.experience.auth.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

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
