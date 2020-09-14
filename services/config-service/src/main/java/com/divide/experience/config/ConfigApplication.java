package com.divide.experience.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by AOleynikov on 17.01.2019.
 *
 * <p>Main class for service.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

    /** Starts the application. */
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
