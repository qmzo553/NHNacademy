package com.nhnacademy.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BasicMovieParser implements MovieParser {

    @Override
    public List<Movie> parse(String fileName) throws IOException {
        List<Movie> movies = new LinkedList<>();
        InputStream in = getMovieFileAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String str = br.readLine();
        System.out.println(str);

        String line;
        while((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            long movieId = Long.parseLong(tokens[0]);
            String title = tokens[1];

            String[] genreToken = tokens[2].split("\\|");
            Set<String> genres = new HashSet<>(Arrays.asList(genreToken));

            Movie movie = new Movie(movieId, title, genres);
            movies.add(movie);
        }

        return movies;
    }
}
