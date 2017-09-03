package com.boris.movieproject.app;

/**
 * Created by boris on 30.08.17.
 * Application controller that reads request from the user
 */

import com.boris.movieproject.config.AppConfig;
import com.boris.movieproject.entity.MovieInterface;
import com.boris.movieproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import java.sql.SQLException;


@Controller
@Configuration
@ComponentScan("com.boris.movieproject.config")
public class AppController {

    @Autowired
    private CronManager myCron;

    @Autowired
    private FileService fileService;

    @Autowired
    private DBService dbService;

    @Autowired
    private InfoService service;

    @Autowired
    private DirectoryHolder directories;



    @RequestMapping("/directory")
    public void getRequest(Model model, @RequestParam(value = "name", required = false, defaultValue = "all movies") String name)  {
        model.addAttribute("name", name);
        directories.addDirectory(name);

    }
}