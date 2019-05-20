package com.divide.experience.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by AOleynikov on 17.01.2019.
 *
 * <p>Configurations of security for access to service.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** Configurations. */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
            .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .httpBasic()
                ;
    }
}
