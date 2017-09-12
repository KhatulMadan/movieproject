package com.boris.movieproject.factory;

import com.boris.movieproject.entity.*;
import org.springframework.stereotype.Component;


@Component
public class MovieFactory {

    /**
     * MovirFactory is used to get AbstractMovie object.
     * @param movieType
     * @return the right class of the movie to be processed.
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    public AbstractMovie getMovie(MovieType movieType) throws IllegalAccessException, InstantiationException {

        AbstractMovie abstractMovie = (AbstractMovie) movieType.getProviderClass().newInstance();
        return abstractMovie;


    }
}


