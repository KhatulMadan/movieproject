package com.boris.movieproject.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boris on 30.08.17.
 * FileService class works with the downloaded movie files.
 */
public class FileService {

    List<String> results = new ArrayList<String>();

    /**
     * Gets the names of the movies in the current folder;
     * @param folderName is the name of the folder with movies;
     * @return the name of all movies in the current folder as a list of strings;
     * If this folderName doesn't denote an existing folder returns null;
     */

    public List<String> getMovies(String folderName) {
        File[] files = new File("/Users/boris/Java/movieproject/" + folderName).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }
}


