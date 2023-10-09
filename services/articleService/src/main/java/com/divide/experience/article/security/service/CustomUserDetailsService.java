package com.divide.experience.article.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SuppressWarnings({"SummaryJavadoc", "InvalidJavadocPosition", "MissingJavadocType"})
public interface CustomUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUsernameWithoutPassword(String email) throws UsernameNotFoundException;

}
