package com.boris.movieproject.service;

import com.boris.movieproject.config.AppConfig;
import com.boris.movieproject.entity.*;
import com.boris.movieproject.factory.MovieFactory;
import com.boris.movieproject.factory.MovieType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

import java.lang.reflect.Field;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by boris on 07.09.17.
 *
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MovieInfoServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private Properties prop;

    MovieFactory movieFactory = new MovieFactory();
    ChildrenMovies cinderella = new ChildrenMovies();
    Movies fightclub = new Movies();




    @Test
    public void saveChildrenMovies() throws Exception {

        cinderella.setDetails("Cinderella",105,"Romance","2015-03-12","Chris Weitz","Kenneth Branagh, Anna Worley, Nicoletta Mani","Lily James, Cate Blanchett, Richard Madden", "When her father unexpectedly passes away, young Ella finds herself at the mercy of her cruel stepmother and her daughters. Never one to give up hope, Ella's fortunes begin to change after meeting a dashing stranger in the woods.","/aUYcExsGuRaw7PLGmAmXubt1dfG.jpg");

        String propFileName = "application.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);
        String key = prop.getProperty("API_KEY");

        Field myAPI = MovieInfoService.class.getDeclaredField("myAPI");
        myAPI.setAccessible(true);
        myAPI.set(movieInfoService, key);

        MovieType movieType = MovieType.valueOf("CHILDRENMOVIES");
        AbstractMovie abstractMovie = movieFactory.getMovie(movieType);
        abstractMovie = movieInfoService.getFullDetails(abstractMovie, "Cinderella");
        ChildrenMovies childrenmovie = (ChildrenMovies) abstractMovie;

            assertEquals(cinderella.getIdchildrenmovies(), childrenmovie.getIdchildrenmovies());
            assertEquals(cinderella.getTitle(), childrenmovie.getTitle());
            assertEquals(cinderella.getGenre(), childrenmovie.getGenre());
            assertEquals(cinderella.getRuntime(), childrenmovie.getRuntime());
            assertEquals(cinderella.getRelease_date(), childrenmovie.getRelease_date());
            assertEquals(cinderella.getDescription(), childrenmovie.getDescription());
            assertEquals(cinderella.getDirector(), childrenmovie.getDirector());
            assertEquals(cinderella.getWritten(), childrenmovie.getWritten());
            assertEquals(cinderella.getCast(), childrenmovie.getCast());

        }

    @Test
    public void saveMovies() throws Exception {

        fightclub.setDetails("Fight Club",139,"Drama","1999-10-15","Chuck Palahniuk, Jim Uhls, Collin Grant","David Fincher, Dina Waxman","Edward Norton, Brad Pitt, Meat Loaf", "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.","/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg");

        String propFileName = "application.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);
        String key = prop.getProperty("API_KEY");

        Field myAPI = MovieInfoService.class.getDeclaredField("myAPI");
        myAPI.setAccessible(true);
        myAPI.set(movieInfoService, key);

        MovieType movieType = MovieType.valueOf("MOVIES");
        AbstractMovie abstractMovie = movieFactory.getMovie(movieType);
        abstractMovie = movieInfoService.getFullDetails(abstractMovie, "Fight Club");
        Movies movies = (Movies) abstractMovie;

        assertEquals(fightclub.getIdMovies(), movies.getIdMovies());
        assertEquals(fightclub.getTitle(), movies.getTitle());
        assertEquals(fightclub.getGenre(), movies.getGenre());
        assertEquals(fightclub.getRuntime(), movies.getRuntime());
        assertEquals(fightclub.getRelease_date(), movies.getRelease_date());
        assertEquals(fightclub.getDescription(), movies.getDescription());
        assertEquals(fightclub.getDirector(), movies.getDirector());
        assertEquals(fightclub.getWritten(), movies.getWritten());
        assertEquals(fightclub.getCast(), movies.getCast());

    }


}

