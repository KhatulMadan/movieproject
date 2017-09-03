package com.boris.movieproject.service;

import com.boris.movieproject.dao.Dao;

import com.boris.movieproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by boris on 31.08.17.
 */
@Service
@Transactional
public class DBServiceImp implements DBService {

    @Autowired
    private Dao myDao;


    @Override
    public void add (Movie movie) {

        if (movie instanceof ChildrenMovies)
        {
            ChildrenMovies childrenmovie = (ChildrenMovies) movie;
            myDao.add(childrenmovie);
        }

        else if (movie instanceof Movies)
        {
            Movies movies = (Movies) movie;
            myDao.add(movies);
        }

        else if (movie instanceof TvShows)
        {
            TvShows show = (TvShows) movie;
            myDao.add(show);
        }

        else if (movie instanceof ChildrenTvShows)
        {
            ChildrenTvShows show = (ChildrenTvShows) movie;
            myDao.add(show);
        }


    }





}
