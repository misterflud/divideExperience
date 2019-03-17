package com.divideExperience.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by AOleynikov on 02.01.2019.
 */
@SpringBootApplication(scanBasePackages = {"com.divideExperience.article"})
public class ArticleApplication {

    public static void main(String[] args) {
       SpringApplication.run(ArticleApplication.class, args);
    }
}
