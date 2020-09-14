package com.divide.experience.gateway;

import com.divide.experience.gateway.filters.OptionCorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableFeignClients("com.divide.experience.lib.api.feign.auth")
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public OptionCorsFilter optionCorsFilter() {
        return new OptionCorsFilter();
    }

}
