package com.boris.movieproject.entity;

/**
 * Created by boris on 03.09.17.
 *
 * Abstract class AbstractMovie.
 * All the movies from the directories (ChildrenMovies, Movies, TvShows, ChildrenTvShows) extend this class.
 *
 */
public abstract class AbstractMovie {

    /**
     * Abstract method to set all the details at one time.
     * @param title
     * @param runtime
     * @param genre
     * @param release_date
     * @param written
     * @param director
     * @param cast
     * @param description
     * @param poster
     */

    public abstract void setDetails(String title,
                                    int runtime,
                                    String genre,
                                    String release_date,
                                    String written,
                                    String director,
                                    String cast,
                                    String description,
                                    String poster);


    /**
     * Abstract method to get the backdrop (poster) path of the movie.
     * This method is used when user wants to download backdrop (poster) as .jpg file.
     * @return backdrop path as a String.
     */

    public abstract String getPoster();
}


