package com.boris.movieproject.app;

/**
 * Created by boris on 30.08.17.
 *
 * Controller class to get requests from the user.
 *
 */


import com.boris.movieproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




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
    private MovieInfoService service;

    @Autowired
    private DirectoryHolder directories;

    @Autowired
    private DownloadService download;

    @Autowired
    private ApplicationContext appContext;


    /**
     * @param name is the name of the directory as a String that contains downloaded movies.
     * The method adds directory to the list of directories (DirectoryHolder class) to be processed.
     */

    @RequestMapping("/directory")
    public String getRequest(Model model, @RequestParam(value = "name", required = false, defaultValue = "all movies") String name)  {
        model.addAttribute("name", name);
        directories.addDirectory(name);
        return "page";

    }
}