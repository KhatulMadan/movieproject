package com.boris.movieproject.factory;

import com.boris.movieproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class MovieFactory {



    public Movie getMovie(MovieType movieType) throws IllegalAccessException, InstantiationException {

        Movie movie = (Movie) movieType.getProviderClass().newInstance();
        return movie;


    }
}


