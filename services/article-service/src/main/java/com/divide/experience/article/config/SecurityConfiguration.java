package com.divide.experience.article.config;

import com.divide.experience.lib.security.configuration.service.HttpFirewallSecurityConfiguration;
import com.divide.experience.lib.security.configuration.service.ResourceServerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({ResourceServerConfiguration.class, HttpFirewallSecurityConfiguration.class})
@ComponentScan(basePackages = {"com.divide.experience.lib.security.configuration"})
public class SecurityConfiguration {

}
