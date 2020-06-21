package com.divide.experience.auth.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public interface CustomUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUsernameWithoutPassword(String email) throws UsernameNotFoundException;

}
