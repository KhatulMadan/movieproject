package com.boris.movieproject.service;

import com.boris.movieproject.dao.Dao;

import com.boris.movieproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by boris on 31.08.17.
 *
 * Service class responsible for performing AbstractMovie object operations.
 */


@Service
@Transactional
public class DBServiceImp implements DBService {

    @Autowired
    private Dao myDao;


    @Override
    public void add (AbstractMovie abstractMovie) {

    myDao.add(abstractMovie);


    }





}
