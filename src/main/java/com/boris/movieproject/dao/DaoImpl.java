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

@Override
    public void add (Movie movie)
{
    sessionFactory.getCurrentSession().save(movie);
}



}
