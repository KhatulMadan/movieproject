package com.boris.movieproject.dao;

import com.boris.movieproject.entity.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by boris on 31.08.17.
 *
 * Implementation of DAO.
 * Repository to perform DB operations using Hibernate.
 *
 */

@Repository
public class DaoImpl implements Dao {

    @Autowired
    private SessionFactory sessionFactory;

@Override
    public void add (AbstractMovie abstractMovie)
{
    sessionFactory.getCurrentSession().save(abstractMovie);
}



}
