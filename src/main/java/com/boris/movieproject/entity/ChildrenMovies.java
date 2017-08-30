package com.boris.movieproject.entity;

/**
 * Created by boris on 30.08.17.
 */

import javax.persistence.*;


@Entity
@Table(name = "childrenmovies")
public class ChildrenMovies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idchildrenmovies;

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

    public ChildrenMovies() {
    }

    public ChildrenMovies(String title,
                          int runtime,
                          String genre,
                          String release_date,
                          String written,
                          String director,
                          String cast,
                          String description,
                          String poster) {
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

    public int getIdchildrenmovies() {
        return idchildrenmovies;
    }

    public void setIdchildrenmovies(int idchildrenmovies) {
        this.idchildrenmovies = idchildrenmovies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getWritten() {
        return written;
    }

    public void setWritten(String written) {
        this.written = written;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}