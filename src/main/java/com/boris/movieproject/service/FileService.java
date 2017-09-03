package com.boris.movieproject.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;


/**
 * Created by boris on 30.08.17.
 * FileService class works with the downloaded movie files.
 */

@Service
@Component
public class FileService {


    /**
     * Gets the names of the movies in the current folder;
     * @param folderName is the name of the folder with movies;
     * @return the name of all movies in the current folder as a list of strings;
     * If this folderName doesn't denote an existing folder returns null;
     */

    public List<String> getMovies(String folderName) {
        File[] files = new File("/Users/boris/Java/movieproject/" + folderName).listFiles();

        List<String> results = new ArrayList<String>();

        // As I'm using Mac I can't iterate through all the results.
        // The point is when you get the names of all files in the path on Mac, every first file is always .DS_Store.
        // So, I exclude this file to get the right list of the movie files in the path.
if (files != null)
{
        for (int i = 1; i < files.length; i++) {

            File file = files[i];

            if (file.isFile()) {


                results.add(FilenameUtils.getBaseName(file.getName()));


            }
        }
        }
        return results;
    }
}


