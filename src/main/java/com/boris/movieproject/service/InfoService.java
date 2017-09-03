package com.boris.movieproject.service;

import com.google.gson.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by boris on 30.08.17.
 * This class is responsible for getting the data from MovieDataBase website
 */

@Service
@Component
public class InfoService {

    /**
     *
     * @param title is the name of the movie that we want to get the details about;
     * @return movie ID as integer;
     */

    public int getID(String title){

        String newTitle;

        if (title.contains(" "))
        {
            newTitle = title.replace(" ", "+");
        }

        else
        {
            newTitle = title;
        }
        String URL = "https://api.themoviedb.org/3/search/movie?api_key=21e9b47319e6cc5ae141b807eaa6c479&query=" + newTitle;
        RestTemplate restTemplate = new RestTemplate();


        int ID = 0;
        JsonObject obj = new JsonParser().parse(restTemplate.getForObject(URL, String.class)).getAsJsonObject();
        JsonArray jarray = obj.getAsJsonArray("results");

        for (JsonElement j : jarray) {
            JsonObject resultObj = j.getAsJsonObject();
            ID =resultObj.get("id").getAsInt();
            if (ID != 0)
            {
                break;
            }
        }

        return ID;

    }


    /**
     * Gets all the movie's details.
     * @param ID is movie ID as integer;
     * @return all the general details as a json string;
     */


    public String getDetails(int ID)
    {

        String newURL = "https://api.themoviedb.org/3/movie/"+ID+"?api_key=21e9b47319e6cc5ae141b807eaa6c479";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(newURL, String.class);

    }



    /**
     *
     * @param ID movie ID as Integer;
     * @return credits as a json string;
     */

    public String getCredits(int ID){
        String URL = "https://api.themoviedb.org/3/movie/"+ID+"/credits?api_key=21e9b47319e6cc5ae141b807eaa6c479";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL, String.class);

    }

    /**
     * Gets the runtime of the movie;
     * @param details is json string with all the details;
     * @return runtime of the movie as an integer;
     */

   public int getRuntime(String details)
   {
       JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
       return obj.get("runtime").getAsInt();

   }

    /**
     * Gets the release date of the movie;
     * @param details is json string with all the details;
     * @return release date of the movie as a string;
     */

   public String getReleaseDate(String details)
   {
       String releaseDate;
       JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
       JsonElement releaseDateObj = obj.get("release_date");

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


   public String getBackdropPath(String details)
   {
       String downloadLink;
       JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
       JsonElement backdropPath = obj.get("backdrop_path");

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

   public String getGenre(String details)
   {
       JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
       JsonArray jarray = obj.getAsJsonArray("genres");
       obj = jarray.get(0).getAsJsonObject();
       return obj.get("name").getAsString();

   }

    /**
     * Gets the short description of the movie's plot;
     * @param details is json string with all the details;
     * @return the short description of the plot as a string;
     */

    public String getOverview(String details)
    {
        String overview;
        JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
        JsonElement overviewObj = obj.get("overview");

        if (overviewObj == null) {
            overview = "no ";
        }

        else
        {
            overview = overviewObj.getAsString();
        }

        return obj.get("overview").getAsString();
    }

    /**
     * Gets the name of the director;
     * @param credits is json string with credits details;
     * @return the name of the director as a string;
     */


   public String getDirector(String credits) {
       String director = "";
       JsonObject obj = new JsonParser().parse(credits).getAsJsonObject();
       JsonArray jarray = obj.getAsJsonArray("crew");

       for (JsonElement j : jarray) {
           JsonObject crewObj = j.getAsJsonObject();
           String job = crewObj.get("job").toString();
           String name = crewObj.get("name").toString();
           director = name;
           if (job.equals("director")) {
               break;
           }
       }


       return director;

   }

    /**
     * Gets the name of the writers (incl. novel authors, screenplay, etc)
     * @param credits is json string with credit details;
     * @return the names of the writers separated by "," as a string;
     */

    public String getWriter(String credits) {
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
        return names.substring(0,names.length()-1);

    }

    /**
     * Gets the name of the main actors;
     * @param credits is json string with credits details;
     * @return the names of three mains actors separated by "," as a string;
     */

    public String getCast(String credits){
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
        return names.substring(0,names.length()-1);

    }


}
