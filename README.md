#MOVIEPROJECT
================

Java micro service built around the [JSON API](http://api.themoviedb.org/) provided by [TMdB](http://themoviedb.org), which is an open database for movie and film content.

# Overview

This micro service automatically checks the downloaded movies in a directory and gets the main infromation about each movie:
- Title.
- Runtime.
- Genre.
- Release date.
- Director.
- Writters.
- Cast (main characters).

All the information is saved to a data base. In addition JPG backdrop/poster for each movie is downloaded and saved to a separate folder.

# How to start

To get started you need a [TMdB API key](http://docs.themoviedb.apiary.io/). 
Sort the movies you download according 4 directories: 
* Movies.
* ChildrenMovies.
* TvShows.
* ChildrenTvShows.

Go to http://localhost:8191/directory?name= and provide directory.

      Example: http://localhost:8191/directory?name=childrenmovies
   
# Built With  

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)  
* [Maven](https://maven.apache.org/) - Dependency Management  
* [Spring Boot](https://projects.spring.io/spring-boot/) - Web Framework 
* [Hibernate](http://hibernate.org) - Framework
* [JUnit](http://junit.org) - Test










 
