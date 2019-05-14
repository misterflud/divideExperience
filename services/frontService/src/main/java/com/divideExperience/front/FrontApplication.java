package com.divideExperience.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by AOleynikov on 04.03.2019.
 */
@SpringBootApplication(scanBasePackages = {"com.divideExperience.front"})
public class FrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }
}
