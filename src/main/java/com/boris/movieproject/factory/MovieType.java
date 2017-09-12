package com.boris.movieproject.factory;

import com.boris.movieproject.entity.ChildrenMovies;
import com.boris.movieproject.entity.ChildrenTvShows;
import com.boris.movieproject.entity.Movies;
import com.boris.movieproject.entity.TvShows;
import org.springframework.stereotype.Component;

/**
 * Created by boris on 05.09.17.
 *
 * All downloaded movies are divided into 4 types declared in this class.
 */

@Component
public enum MovieType {

    CHILDRENMOVIES (1, ChildrenMovies.class),
    MOVIES (2, Movies.class),
    TVSHOWS (3, TvShows.class),
    CHILDRENTVSHOWS (4, ChildrenTvShows.class);

    private int id;
    private Class providerClass;



    MovieType(int id, Class clz) {
        this.id = id;
        this.providerClass = clz;
    }


    public int getId() {
        return id;
    }

    public Class getProviderClass() {
        return providerClass;
    }


}