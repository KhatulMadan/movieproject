package com.boris.movieproject.dao;


import com.boris.movieproject.entity.*;

/**
 * Created by boris on 31.08.17.
 *
 */
public interface Dao {

    void add(ChildrenMovies movie);
    void add(Movies movie);
    void add(TvShows movie);
    void add(ChildrenTvShows movie);


}
