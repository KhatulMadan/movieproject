package com.boris.movieproject.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by boris on 03.09.17.
 */
@Service
public class DirectoryHolder {


    private Set<String> directories = new HashSet();

    public Set<String> getDirectories () {
        return directories;
    }

    public void addDirectory(String directoryName){
        directories.add(directoryName);

    }


}

