package com.boris.movieproject.app;

/**
 * Created by boris on 30.08.17.
 * AppLoader class loads the application
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableScheduling
@SpringBootApplication
public class AppLoader extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppLoader.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppLoader.class, args);
    }
}
