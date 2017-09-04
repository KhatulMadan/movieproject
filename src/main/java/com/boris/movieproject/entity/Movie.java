package com.boris.movieproject.entity;

/**
 * Created by boris on 03.09.17.
 *
 * Abstract class Movie.
 * All the movies from the directories (ChildrenMovies, Movies, TvShows, ChildrenTvShows) extend this class.
 *
 */
public abstract class Movie {

    public abstract void setDetails(String title,
                                    int runtime,
                                    String genre,
                                    String release_date,
                                    String written,
                                    String director,
                                    String cast,
                                    String description,
                                    String poster);


}
