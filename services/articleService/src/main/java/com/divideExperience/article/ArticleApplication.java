package com.divideExperience.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Logger;


/**
 * Created by AOleynikov on 02.01.2019.
 */
//@EnableDiscoveryClient
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication(scanBasePackages = {"com.divideExperience.article"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ArticleApplication {
    static Logger LOG = Logger.getLogger(ArticleApplication.class.getName());;
    public static void main(String[] args) {
        System.out.println("1");
//        try (ConfigurableApplicationContext context =
//                     SpringApplication.run(ArticleApplication.class, args)) {
//            LOG.info("context: " + context);
//        }

        SpringApplication.run(ArticleApplication.class, args);
        System.out.println("2");
    }
}
