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
 */

@Repository
public class DaoImpl implements Dao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(ChildrenMovies movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    @Override
    public void add(Movies movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    @Override
    public void add(TvShows movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    @Override
    public void add(ChildrenTvShows movie) {
        sessionFactory.getCurrentSession().save(movie);
    }



}
