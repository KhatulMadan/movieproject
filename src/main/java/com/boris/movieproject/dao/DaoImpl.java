package com.boris.movieproject.dao;

import com.boris.movieproject.entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * Created by boris on 31.08.17.
 *
 * Implementation of DAO
 * Repository to perform DB operations using Hibernate
 *
 */

@Repository
public class DaoImpl implements Dao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Adds ChildrenMovies object
     * @param movie is ChildrenMovies object to be added
     */

    @Override
    public void add(ChildrenMovies movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    /**
     * Adds Movies object
     * @param movie is Movies object to be added
     */

    @Override
    public void add(Movies movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    /**
     * Adds TvShows object
     * @param movie is TvShows object to be added
     */

    @Override
    public void add(TvShows movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    /**
     * Adds ChildrenTvShows object
     * @param movie is ChildrenTvShows object to be added
     */

    @Override
    public void add(ChildrenTvShows movie) {
        sessionFactory.getCurrentSession().save(movie);
    }



}
