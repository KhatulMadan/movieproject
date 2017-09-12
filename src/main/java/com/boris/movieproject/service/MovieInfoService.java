package com.boris.movieproject.service;

import com.boris.movieproject.config.AppConfig;
import com.boris.movieproject.entity.AbstractMovie;
import com.boris.movieproject.factory.MovieFactory;
import com.boris.movieproject.factory.MovieType;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Created by boris on 30.08.17.
 *
 * Service class fetching the data from MovieDataBase website using MovieDataBase API and saving it to MySQL data base.
 *
 */


@Service
@Component
public class MovieInfoService {


    @Autowired
    private DBService dbService;

    @Autowired
    private DownloadService downloadService;

    // API Key provided by TheMovieDataBase.

    @Value("${API_KEY}")
    private String myAPI;

    // Basic URLs used by TheMovieDataBase API to make queries.

    private final String basicURL = "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s";
    private final String detailsQueryURL = "https://api.themoviedb.org/3/movie/%s?api_key=%s";
    private final String creditsQueryURL = "https://api.themoviedb.org/3/movie/%s/credits?api_key=%s";

    private int ID;
    private String details;
    private String credits;

    /**
     * Search the basic information about a movie using its title.
     * Fetch the id of the movie in TheMovieDataBase and save to variable ID.
     * @param title of the movie to be processed.
     *
     */

    private void setID(String title){

        String newTitle;

        if (title.contains(" "))
        {
            newTitle = title.replace(" ", "+");
        }

        else
        {
            newTitle = title;
        }
        String getURL=String.format(basicURL, myAPI, newTitle);

        System.out.println(getURL);

        RestTemplate restTemplate = new RestTemplate();


        JsonObject obj = new JsonParser().parse(restTemplate.getForObject(getURL, String.class)).getAsJsonObject();
        JsonArray jarray = obj.getAsJsonArray("results");

        for (JsonElement j : jarray) {
            JsonObject resultObj = j.getAsJsonObject();
            ID =resultObj.get("id").getAsInt();
            if (ID != 0)
            {
                break;
            }
        }



    }


    /**
     * Get the general information about a movie.
     * Save the received data as Json String to variable details.
     * @param ID is movie's id in TheMovieDataBase.
     */


    private void setDetails(int ID)
    {

        String getURL=String.format(detailsQueryURL, ID, myAPI);

        //
        System.out.println(getURL);

        RestTemplate restTemplate = new RestTemplate();
        details = restTemplate.getForObject(getURL, String.class);


    }


    /**
     * Get the information about cast and crew of a movie.
     * Save the received data as Json String to variable credits.
     * @param ID is movie's id in TheMovieDataBase.
     */

    private void setCredits(int ID){
        String getURL=String.format(creditsQueryURL, ID, myAPI);

        //
        System.out.println(getURL);

        RestTemplate restTemplate = new RestTemplate();
        credits = restTemplate.getForObject(getURL, String.class);

    }

    /**
     * Helper method to get the runtime of a movie.
     * @param details is JSon String with all general details.
     * @return runtime of a movie as an Integer.
     */

   private int getRuntime(String details)
   {
       JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
       return detailsObj.get("runtime").getAsInt();

   }

    /**
     * Helper method to get the release date of a movie.
     * @param details is JSon String with all general details.
     * @return release date of a movie as a String.
     */

   private String getReleaseDate(String details)
   {
       String releaseDate;
       JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
       JsonElement releaseDateObj = detailsObj.get("release_date");

       if (releaseDateObj == null) {
           releaseDate = "unknown";
       }

       else
       {
           releaseDate = releaseDateObj.getAsString();
       }

       return releaseDate;
   }

    /**
     * Helper method to get the backdrop (poster) path of a movie.
     * @param details is JSon String with all general details.
     * @return backdrop (poster) path as a String.
     */


   private String getBackdropPath(String details)
   {
       String downloadLink;
       JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
       JsonElement backdropPath = detailsObj.get("backdrop_path");

       if (backdropPath == null) {
           downloadLink = "no link available";
       }

       else
       {
           downloadLink = backdropPath.toString();
       }

       return downloadLink;

   }

    /**
     * Helper method to get the genre of a movie.
     * @param details is JSon String with all general details.
     * @return genre as a String;
     */

   private String getGenre(String details)
   {
       JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
       JsonArray jarray = detailsObj.getAsJsonArray("genres");
       JsonObject obj = jarray.get(0).getAsJsonObject();
       return obj.get("name").getAsString();

   }

    /**
     * Helper method to get a short description (overview) of a movie's plot.
     * @param details is JSon String with all general details.
     * @return overview as a String.
     */

    private String getOverview(String details)
    {
        String overview;
        JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
        JsonElement overviewObj = detailsObj.get("overview");

        if (overviewObj != null) {
            overview = overviewObj.getAsString();
        }

        else
        {
            overview = "not available";
        }

        return overview;
    }

    /**
     * Helper method to get the director of a movie.
     * @param credits is JSon String with movie credits (cast, crew).
     * @return director's name as a String.
     */


   private String getDirector(String credits) {
       StringBuilder director = new StringBuilder();
       String sep = ", ";
       JsonObject obj = new JsonParser().parse(credits).getAsJsonObject();
       JsonArray jarray = obj.getAsJsonArray("crew");

       for (JsonElement j : jarray) {
           JsonObject crewObj = j.getAsJsonObject();
           String department = crewObj.get("department").getAsString();
           String name = crewObj.get("name").getAsString();
           if (department.equals("Directing")) {
               director.append(name);
               director.append(sep);
           }
       }


       String name = director.toString();
       return name.substring(0,name.length()-2);

   }

    /**
     * Helper method to get the name of a movie's writers (incl. novel authors, screenplay, etc).
     * @param credits is JSon String with movie credits (cast, crew).
     * @return the writers' names separated by comma as a String.
     */

    private String getWriter(String credits) {
        StringBuilder written = new StringBuilder();
        String sep = ", ";
        JsonObject obj = new JsonParser().parse(credits).getAsJsonObject();
        JsonArray jarray = obj.getAsJsonArray("crew");
        for (JsonElement j : jarray) {
            JsonObject crewObj = j.getAsJsonObject();
            String     department     = crewObj.get("department").getAsString();
            String     name = crewObj.get("name").getAsString();
            if (department.equals("Writing"))
            {
                written.append(name);
                written.append(sep);
                }
            }

        String names = written.toString();
        return names.substring(0,names.length()-2);

    }

    /**
     * Helper method to get movie's cast.
     * @param credits is JSon String with movie credits (cast, crew).
     * @return the names of three mains actors separated by comma as a String.
     */

    private String getCast(String credits){
        StringBuilder starring = new StringBuilder();
        String sep = ", ";
        JsonObject obj = new JsonParser().parse(credits).getAsJsonObject();
        JsonArray jarray = obj.getAsJsonArray("cast");
        for (int i = 0; i<3; i++) {
            JsonObject castObj = jarray.get(i).getAsJsonObject();
            String name = castObj.get("name").getAsString();

                starring.append(name);
                starring.append(sep);


        }

        String names = starring.toString();
        return names.substring(0,names.length()-2);

    }

    /**
     * Get all the needed details of the movie and sets it to an instance of an AbstractMovie object.
     * @param abstractMovie is an empty instance of Abstract Movie object.
     * @param title is the title of a movie.
     * @return an instance of Abstract Movie object with all the values set.
     */

   public AbstractMovie getFullDetails(AbstractMovie abstractMovie, String title)
    {
        setID(title);
        setDetails(ID);
        setCredits(ID);
        abstractMovie.setDetails(title,
                getRuntime(details), getGenre(details), getReleaseDate(details),
                getWriter(credits), getDirector(credits), getCast(credits), getOverview(details), getBackdropPath(details));
        return abstractMovie;

    }

    /**
     * Process all the downloaded movie from a directory.
     * Save all the details to MySQL data base.
     * @param results is a list of movies in a directory.
     * @param movieType is the type of downloaded movies in a directory
     *                  (e.g. Children Movie, Movie, TV Show, Children TV Show).
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     */

    public void saveMovies(List<String> results, MovieType movieType) throws InstantiationException, IllegalAccessException, IOException {

        MovieFactory movieFactory = new MovieFactory();
        AbstractMovie abstractMovie = movieFactory.getMovie(movieType);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        dbService = context.getBean(DBService.class);


        for (int i = 0; i<results.size(); i++) {

            String title = results.get(i);
            abstractMovie = getFullDetails(abstractMovie, title);
            String filepath = abstractMovie.getPoster();
            downloadService.downloadFile(filepath);
            dbService.add(abstractMovie);

        }

    }


}
