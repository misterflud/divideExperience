package com.divide.experience.auth.services.impl;

import com.divide.experience.auth.objects.domain.UserModel;
import com.divide.experience.auth.security.DivideUserDetails;
import com.divide.experience.auth.services.CustomUserDetailsService;
import com.divide.experience.auth.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
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
        return DivideUserDetails
            .builder()
            .id(userModel.getId())
            .email(userModel.getEmail())
            .nickName(userModel.getNickName())
            .password(userModel.getPassword())
            .authorities(getRoles(userModel))
            .build();
    }

    @Override
    public UserDetails loadUserByUsernameWithoutPassword(String email) throws UsernameNotFoundException {
        UserModel userModel = userService.getUserByEmail(email);
        return DivideUserDetails
            .builder()
            .id(userModel.getId())
            .email(userModel.getEmail())
            .nickName(userModel.getNickName())
            .authorities(getRoles(userModel))
            .build();
    }

    private Set<GrantedAuthority> getRoles(UserModel userModel) {
        return userModel.getUserRoles().stream()
            .map(r -> new SimpleGrantedAuthority(r.getName()))
            .collect(Collectors.toSet());
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
