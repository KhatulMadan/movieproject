package com.boris.movieproject.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;


/**
 * Created by boris on 30.08.17.
 *
 * Service class providing the names of downloaded movies stored in a directory.
 */

@Service
@Component
public class FileService {


    /**
     * Get the names of the movies in the current directory.
     *
     * @param folderName is the name of directory with downloaded movies.
     * @return the name of all movies in a current directory as a list of strings.
     * If folderName doesn't denote an existing folder returns null;
     */

    public List<String> getMovies(String folderName) {
        List<String> results = new ArrayList<String>();
        String home = System.getProperty("user.home");
        File folder = new File(home+"/Java/movieproject/" + folderName);


        // I exclude .DS_Store file to get the right list of the movie files in the path if app is used on macOs.


            File[] files = folder.listFiles((dir, name) -> !name.equals(".DS_Store"));

        if (files != null)
        {
            for (File f: files)
            {
                if (f.isFile()) {


                    results.add(FilenameUtils.getBaseName(f.getName()));


                }
            }
        }

        return results;
    }
}


