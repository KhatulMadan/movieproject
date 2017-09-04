package com.boris.movieproject.service;

import com.boris.movieproject.entity.*;
import org.springframework.stereotype.Component;

/**
 * Created by boris on 03.09.17.
 *
 * Factory to generate object of concrete class based on given information.
 */

@Component
public class MovieFactory {

public Movie getMovie(String movie){

        if(movie == null){
            return null;
        }
        if(movie.equals("CHILDRENMOVIES")){
            return new ChildrenMovies();

        } else if(movie.equals("MOVIES")){
            return new Movies();

        } else if(movie.equals("TVSHOWS")){
            return new TvShows();

        } else if(movie.equals("CHILDRENTVSHOWS")){
        return new ChildrenTvShows();
    }

        return null;
    }





    }


