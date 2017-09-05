package com.boris.movieproject.service;

import com.boris.movieproject.config.AppConfig;
import com.boris.movieproject.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FileService fileService;

    @Autowired
    private DBService dbService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private DirectoryHolder directories;


    @Autowired
    private MovieFactory movieFactory;

    @Autowired
    private DownloadService downloadService;


    /**
     * The method that executes the scheduled task.
     * All movies from the adjusted directory are processed in order to get the certain information and save it to DB.
     * Also the backdrop for each is downloaded as .jpg file.
     * @throws IOException
     */


    @Scheduled(cron = "0 */5 * ? * *")
    public void cronTask() throws IOException {

        for (String d : directories.getDirectories()) {



            List<String> results = fileService.getMovies(d);
            AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
            dbService = context.getBean(DBService.class);



        for (int i = 0; i<results.size(); i++) {


            String title = results.get(i);
            Movie movie = movieFactory.getMovie(d.toUpperCase());
            movie = infoService.getFullDetails(movie, title);
            String filepath = movie.getPoster();
            downloadService.downloadFile(filepath);
            dbService.add(movie);

        }



        }


    }
}
