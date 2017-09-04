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
    private InfoService service;

    @Autowired
    private DirectoryHolder directories;


    @Autowired
    private MovieFactory movieFactory;

    @Autowired
    private DownloadService download;


    /**
     * The method that executes the scheduled task.
     * All movies from the adjusted directory are processed in order to get the certain information and save it to DB.
     * Also the backdrop for each is downloaded as .jpg file.
     * @throws IOException
     */


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

            System.out.println("You have choosed movie: " + title);


            int movieID = service.getID(title);
            System.out.println("movie ID: " + movieID);

            String details = service.getDetails(movieID);
            System.out.println("Full details: " + details);

            String credits = service.getCredits(movieID);
            System.out.println("Json credits: " + credits);

            int runtime = service.getRuntime(details);

            System.out.println("Runtime: " + runtime);


            String genre = service.getGenre(details);


            System.out.println("Genre: " + genre);


            String release = service.getReleaseDate(details);


            System.out.println("release date: " + release);


            String overview = service.getOverview(details);


            System.out.println("overview: " + overview);


            String filepath = service.getBackdropPath(details);


            System.out.println("Link to poster: " + filepath);





            String director = service.getDirector(credits);


            System.out.println("Director: " + director);


            String writers = service.getWriter(credits);


            System.out.println("Written by: " + writers);


            String actors = service.getCast(credits);


            System.out.println("Starring: " + actors);

            movie.setDetails(title, runtime, genre, release, writers, director, actors, overview, filepath);


            download.downloadFile(filepath);

       
             dbService.add(movie);


        }



        }


    }
}
