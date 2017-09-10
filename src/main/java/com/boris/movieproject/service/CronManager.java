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
            movieInfoService.saveMovies(results, movieType);


        }


    }
}
