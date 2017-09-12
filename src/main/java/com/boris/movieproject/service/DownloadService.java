package com.boris.movieproject.service;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by boris on 30.08.17.
 *
 * Service class to download backdrops (posters) as .jpg files from MoviesDataBase web site.
 */

@Component
public class DownloadService {

    private static final int BUFFER_SIZE = 4096;

    //default link provided by MoviesDataBase website

    private static final String basicUrl = "https://image.tmdb.org/t/p/original";

    /**
     * Downloads a file from a URL
     * @param fileURL the part of the url that points to a certain jpg file;
     * @throws IOException
     */

    public static void downloadFile(String fileURL)
            throws IOException {

        String fullURL = basicUrl+fileURL.substring(1, fileURL.length()-1);
        System.out.println(fullURL);
        URL url = new URL(fullURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field

                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }


            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length()-1);
            }

            System.out.println("Content-type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = "/Users/boris/Java/movieproject/posters" + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}

