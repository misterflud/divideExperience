package com.divide.experience.auth.config;

import com.divide.experience.auth.filters.JwtAuthenticationServiceFilter;
import com.divide.experience.auth.filters.JwtAuthenticationUserFilter;
import com.divide.experience.auth.filters.LoginAuthenticationFilter;
import com.divide.experience.auth.security.JwtAuthenticationTokenProvider;
import com.divide.experience.auth.security.JwtTokenProcessor;
import com.divide.experience.auth.services.impl.UserDetailsServiceImpl;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.FORBIDDEN;


/**
 * Created by AOleynikov on 27.05.2019.
 */
@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationTokenProvider jwtProvider;
    private JwtTokenProcessor jwtTokenProcessor;
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll() // здесь мы получить токен
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginAuthenticationFilter("/login", authenticationManagerBean(), jwtTokenProcessor),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationUserFilter("/checkToken", authenticationManagerBean()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationServiceFilter("/user_details", authenticationManagerBean()),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
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

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(FORBIDDEN);
    }

    @Resource
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Resource
    public void setJwtProvider(JwtAuthenticationTokenProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Resource
    public void setJwtTokenProcessor(JwtTokenProcessor jwtTokenProcessor) {
        this.jwtTokenProcessor = jwtTokenProcessor;
    }
}
