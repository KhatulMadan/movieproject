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

        FileService directory = new FileService();
        List<String> results = directory.getMovies(name);
        InfoService service = new InfoService();
        String title = results.get(1);

        System.out.println("You have choosed movie: " + title);
        String details = service.getDetails(title);

        int i = service.getRuntime(details);
        System.out.println("Runtime: " + title);

        String genre = service.getGenre(details);
        System.out.println("Genre: " + genre);

        String release = service.getReleaseDate(details);
        System.out.println("release date: " + release);

        String overview = service.getOverview(details);
        System.out.println("overview: " + overview);

        String director = service.getDirector(details);
        System.out.println("Director: " + director);

        String writers = service.getWriter(details);
        System.out.println("Written by: " + writers);

        String actors = service.getCast(details);
        System.out.println("Starring: " + actors);

        String filepath = service.getBackdropPath(details);
        System.out.println("Link to poster: " + filepath);

        return "hello";
    }
}