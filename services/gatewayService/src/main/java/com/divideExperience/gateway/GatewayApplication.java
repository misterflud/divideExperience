package com.divideExperience.gateway;

import com.divideExperience.gateway.filters.OptionCORSFilter;
import com.divideExperience.gateway.filters.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

    @Bean
    public OptionCORSFilter OptionCORSFilter() {
        return new OptionCORSFilter();
    }

}
