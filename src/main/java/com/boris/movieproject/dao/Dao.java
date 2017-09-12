package com.boris.movieproject.dao;


import com.boris.movieproject.entity.*;

/**
 * Created by boris on 31.08.17.
 *
 * DAO interface.
 */


public interface Dao {

  /**
   * Add new AbstractMovie object to repository.
   * @param abstractMovie is new AbstractMovie object to be added.
   */

  void add (AbstractMovie abstractMovie);


}
