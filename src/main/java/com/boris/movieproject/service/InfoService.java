package com.boris.movieproject.service;

import com.google.gson.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by boris on 30.08.17.
 * This class is responsible for getting the data from MovieDataBase website
 */
public class InfoService {

    /**
     * Gets all the movie's details.
     * @param title is the name of the movie that we want to get the details about;
     * @return all the data that we need as one json string;
     */


    public String getDetails(String title)
    {

        String newTitle = "";

        if (title.contains(" "))
        {
            newTitle = title.replace(" ", "+");
        }

        else
        {
            newTitle = title;
        }
        String URL = "https://api.themoviedb.org/3/search/movie?api_key=APIKEY=" + newTitle;
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(URL, String.class);
        JsonObject obj = new JsonParser().parse(jsonString).getAsJsonObject();
        int ID = obj.get("id").getAsInt();

        String newURL = "https://api.themoviedb.org/3/movie/"+ID+"?api_key=APIKEY&append_to_response=credits";
        restTemplate = new RestTemplate();
        String details = restTemplate.getForObject(newURL, String.class);
        return details;
    }

    /**
     * Gets the runtime of the movie;
     * @param details is json string with all the details;
     * @return tunrime of the movie as an integer;
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
       JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
       return obj.get("release_date").getAsString();
   }

    /**
     * Gets the poster of the movie;
     * @param details is json string with all the details;
     * @return the link to the poster (jpg file) as a string;
     */


   public String getBackdropPath(String details)
   {
       JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
       return obj.get("backdrop_path").getAsString();
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
        JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
        return obj.get("overview").getAsString();
    }

    /**
     * Gets the name of the director;
     * @param details is json string with all the details;
     * @return the name of the director as a string;
     */


   public String getDirector(String details) {
       String director = "";
       JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
       JsonArray jarray = obj.getAsJsonArray("crew");
       for (JsonElement j : jarray) {
           JsonObject crewObj = j.getAsJsonObject();
           String     job     = crewObj.get("job").getAsString();
           String     name = crewObj.get("name").getAsString();
           if (job.equals("director"))
           {
               director = name;
               break;
           }
       }

       return director;

   }

    /**
     * Gets the name of the writers (incl. novel authors, screenplay, etc)
     * @param details is json string with all the details;
     * @return the names of the writers separated by "," as a string;
     */

    public String getWriter(String details) {
        StringBuilder written = new StringBuilder();
        String sep = ",";
        JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
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
     * @param details is json string with all the details;
     * @return the names of three mains actors separated by "," as a string;
     */

    public String getCast(String details){
        StringBuilder starring = new StringBuilder();
        JsonObject obj = new JsonParser().parse(details).getAsJsonObject();
        JsonArray jarray = obj.getAsJsonArray("cast");
        for (int i = 0; i<3; i++) {
            JsonObject castObj = jarray.get(i).getAsJsonObject();
            String name = castObj.get("name").getAsString();
                String sep = "";
                starring.append(sep);
                starring.append(name);
                sep = ",";

        }

        String names = starring.toString();
        return names.substring(0,names.length()-1);

    }


}
