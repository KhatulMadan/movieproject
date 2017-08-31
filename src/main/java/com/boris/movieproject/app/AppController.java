package com.boris.movieproject.app;

/**
 * Created by boris on 30.08.17.
 * Application controller that reads request from the user
 */

import com.boris.movieproject.service.FileService;
import com.boris.movieproject.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AppController {


    @RequestMapping("/directory")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "all movies") String name) {
        model.addAttribute("name", name);


        return "hello";
    }
}