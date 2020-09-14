package com.divide.experience.lib.security.configuration.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
public class HttpFirewallSecurityConfiguration {

    /**
     * Исправляет ошибку:
     * "The request was rejected because the URL contained a potentially malicious String".
     *
     * @return firewall по умолчанию.
     */
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
