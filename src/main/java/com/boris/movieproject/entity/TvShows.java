package com.boris.movieproject.entity;

/**
 * Created by boris on 30.08.17.
 *
 * Entity class for TvShows
 * All the information about downloaded movies from directory TvShows is saved to it's own table.
 *
 */

import javax.persistence.*;


@Entity
@Table(name = "tvshows")
public class TvShows extends AbstractMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtvshows;

    @Column(name = "title")
    private String title;

    @Column(name = "runtime")
    private int runtime;

    @Column(name = "genre")
    private String genre;

    @Column(name = "release_date")
    private String release_date;

    @Column(name = "written")
    private String written;

    @Column(name = "director")
    private String director;

    @Column(name = "cast")
    private String cast;

    @Column(name = "description")
    private String description;

    @Column(name = "poster")
    private String poster;

    public TvShows() {
    }



    public int getIdchildrenmovies() {
        return idtvshows;
    }

    public String getTitle() {
        return title;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getWritten() {
        return written;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getDescription() {
        return description;
    }

    public String getPoster() {
        return poster;
    }

    /**
     * General setter method to set all the details of a TV Show at one time.
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

    public void setDetails(String title,
                           int runtime,
                           String genre,
                           String release_date,
                           String written,
                           String director,
                           String cast,
                           String description,
                           String poster)
    {
        this.title = title;
        this.runtime = runtime;
        this.genre = genre;
        this.release_date = release_date;
        this.written = written;
        this.director = director;
        this.cast = cast;
        this.description = description;
        this.poster = poster;
    }
}