package com.divide.experience.article.security;

import com.divide.experience.article.security.filters.JwtAuthenticationUserFilter;
import com.divide.experience.article.security.filters.LoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Created by AOleynikov on 11.01.2020.
 */
@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationTokenProvider jwtProvider;

    private JwtTokenProcessor jwtTokenProcessor;

    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(FORBIDDEN);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/p/**", "/**/p")
            .authenticated()
            .and()
            .addFilterBefore(new JwtAuthenticationUserFilter("/article/p/**", authenticationManagerBean()),
                UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new LoginAuthenticationFilter("/auth/login", authenticationManagerBean(), jwtTokenProcessor),
                UsernamePasswordAuthenticationFilter.class)
            .csrf().disable()
            .cors();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(jwtProvider)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder()); // for login
    }

    @Autowired
    public void setJwtProvider(JwtAuthenticationTokenProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    public void setJwtTokenProcessor(JwtTokenProcessor jwtTokenProcessor) {
        this.jwtTokenProcessor = jwtTokenProcessor;
    }

    @Resource
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
