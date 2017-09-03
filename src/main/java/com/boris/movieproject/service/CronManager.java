package com.boris.movieproject.service;

import com.boris.movieproject.config.AppConfig;
import com.boris.movieproject.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


/**
 * Created by boris on 31.08.17.
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
    private InfoService service;

    @Autowired
    private DirectoryHolder directories;


    @Autowired
    private MovieFactory movieFactory;






    @Scheduled(cron = "0 */5 * ? * *")
    public void cronTask() throws IOException {

        for (String d : directories.getDirectories()) {


            Movie movie = movieFactory.getMovie(d.toUpperCase());


            List<String> results = fileService.getMovies(d);


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        dbService = context.getBean(DBService.class);



        for (int i = 0; i<results.size(); i++) {


            String title = results.get(i);
            movie.setTitle(title);
            int movieID = service.getID(title);
            String details = service.getDetails(movieID);
            String credits = service.getCredits(movieID);
            int runtime = service.getRuntime(details);
            movie.setRuntime(runtime);
            String genre = service.getGenre(details);
            movie.setGenre(genre);
            String release = service.getReleaseDate(details);
            movie.setRelease_date(release);
            String overview = service.getOverview(details);
            movie.setDescription(overview);
            String filepath = service.getBackdropPath(details);
            movie.setPoster(filepath);
            DownloadService download = new DownloadService();
            download.downloadFile(filepath);
            String director = service.getDirector(credits);
            movie.setDirector(director);
            String writers = service.getWriter(credits);
            movie.setWritten(writers);
            String actors = service.getCast(credits);
            movie.setCast(actors);
            dbService.add(movie);


        }



        }


    }
}
