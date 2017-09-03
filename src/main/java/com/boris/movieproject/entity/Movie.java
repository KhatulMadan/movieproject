package com.boris.movieproject.entity;

/**
 * Created by boris on 03.09.17.
 */
public abstract class Movie {

    public abstract void setTitle(String title);
    public abstract void setRuntime(int runtime);
    public abstract void setGenre(String genre);
    public abstract void setRelease_date(String release_date);
    public abstract void setWritten(String written);
    public abstract void setDirector(String director);
    public abstract void setCast(String cast);
    public abstract void setDescription(String description);
    public abstract void setPoster(String poster);

}
