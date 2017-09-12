package com.boris.movieproject.service;


import com.boris.movieproject.factory.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MovieInfoService movieInfoService;


    @Autowired
    private DirectoryHolder directories;

    @Autowired
    private FileService fileService;



    /**
     * Take the directories and process every movie one by one.
     * Automatically repeat the task after certain period of time.
     * (By default the task is executed every 5 minutes).
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */


    @Scheduled(cron = "0 */5 * ? * *")
    public void cronTask() throws IOException, InstantiationException, IllegalAccessException {

        for (String d : directories.getDirectories()) {

            List<String> results = fileService.getMovies(d);
            MovieType movieType = MovieType.valueOf(d.toUpperCase());
            movieInfoService.saveMovies(results, movieType);

        }


    }
}
