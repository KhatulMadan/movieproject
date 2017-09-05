package com.boris.movieproject.service;

import com.boris.movieproject.entity.Movie;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by boris on 30.08.17.
 *
 * Service class fetching the data from MovieDataBase website
 */

@Service
@Component
public class InfoService {



    @Value("${API_KEY}")
    private String myAPI;

    private final String basicURL = "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s";
    private final String detailsQueryURL = "https://api.themoviedb.org/3/movie/%s?api_key=%s";
    private final String creditsQueryURL = "https://api.themoviedb.org/3/movie/%s/credits?api_key=%s";

    private int ID;
    private String details;
    private String credits;



    /**
     *
     * @param title is the name of the movie that we want to get the details about;
     * This method assigns variable ID;
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

        //
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
     * Method saves all movie details in variable details;
     * @param ID is movie ID as integer;
     *
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
     * Method saves all movie credits in variable credits;
     * @param ID movie ID as Integer;
     *
     */

    private void setCredits(int ID){
        String getURL=String.format(creditsQueryURL, ID, myAPI);

        //
        System.out.println(getURL);

        RestTemplate restTemplate = new RestTemplate();
        credits = restTemplate.getForObject(getURL, String.class);

    }

    /**
     * Gets the runtime of the movie;
     * @param details is json string with all the details;
     * @return runtime of the movie as an integer;
     */

   private int getRuntime(String details)
   {
       JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
       return detailsObj.get("runtime").getAsInt();

   }

    /**
     * Gets the release date of the movie;
     * @param details is json string with all the details;
     * @return release date of the movie as a string;
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
     * Gets the poster of the movie;
     * @param details is json string with all the details;
     * @return the link to the poster (jpg file) as a string;
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
     * Gets the genre of the movie;
     * @param details is json string with all the details;
     * @return genre as a string;
     */

   private String getGenre(String details)
   {
       JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
       JsonArray jarray = detailsObj.getAsJsonArray("genres");
       JsonObject obj = jarray.get(0).getAsJsonObject();
       return obj.get("name").getAsString();

   }

    /**
     * Gets the short description of the movie's plot;
     * @param details is json string with all the details;
     * @return the short description of the plot as a string;
     */

    private String getOverview(String details)
    {
        String overview;
        JsonObject detailsObj = new JsonParser().parse(details).getAsJsonObject();
        JsonElement overviewObj = detailsObj.get("overview");

        if (overviewObj == null) {
            overview = "no ";
        }

        else
        {
            overview = overviewObj.getAsString();
        }

        return detailsObj.get("overview").getAsString();
    }

    /**
     * Gets the name of the director;
     * @param credits is json string with credits details;
     * @return the name of the director as a string;
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
     * Gets the name of the writers (incl. novel authors, screenplay, etc)
     * @param credits is json string with credit details;
     * @return the names of the writers separated by "," as a string;
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
     * Gets the name of the main actors;
     * @param credits is json string with credits details;
     * @return the names of three mains actors separated by "," as a string;
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
     * @param movie is an empty movie object.
     * @param title is the title of the movie.
     * @return movie with all the values set.
     */

    public Movie getFullDetails(Movie movie, String title)
    {
        setID(title);
        setDetails(ID);
        setCredits(ID);
        movie.setDetails(title,
                getRuntime(details), getGenre(details), getReleaseDate(details),
                getWriter(credits), getDirector(credits), getCast(credits), getOverview(details), getBackdropPath(details));
        return movie;

    }


}
