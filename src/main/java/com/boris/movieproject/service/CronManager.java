package com.boris.movieproject.service;

import com.boris.movieproject.config.AppConfig;
import com.boris.movieproject.entity.Movie;
import com.boris.movieproject.factory.MovieFactory;
import com.boris.movieproject.factory.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


/**
 * Created by boris on 31.08.17.
 *
 * Service class storing and executing the scheduled tasks.
 */

@Component
public class CronManager {

    @Autowired
    private CronManager myCron;


    @Autowired
    private InfoService infoService;

    @Autowired
    private DirectoryHolder directories;

    @Autowired
    private FileService fileService;








    /**
     * The method that executes the scheduled task.
     * All movies from the adjusted directory are processed in order to get the certain information and save it to DB.
     * Also the backdrop for each is downloaded as .jpg file.
     * @throws IOException
     */


    @Scheduled(cron = "0 */5 * ? * *")
    public void cronTask() throws IOException, InstantiationException, IllegalAccessException {

        for (String d : directories.getDirectories()) {

            List<String> results = fileService.getMovies(d);
            MovieType movieType = MovieType.valueOf(d.toUpperCase());
            infoService.saveMovies(results, movieType);


        }


    }
}
