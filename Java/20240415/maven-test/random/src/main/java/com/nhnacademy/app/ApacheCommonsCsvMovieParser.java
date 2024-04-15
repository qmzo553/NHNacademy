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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ApacheCommonsCsvMovieParser implements MovieParser {

    @Override
    public List<Movie> parse(String fileName) throws IOException {
        List<Movie> movies = new LinkedList<>();
        InputStream in = getMovieFileAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(br);
        for(CSVRecord csvRecord : parser) {
            long movieId = Long.parseLong(csvRecord.get("movieId"));
            String title = csvRecord.get("title");

            String[] genreToken = csvRecord.get("genres").split("\\|");
            Set<String> genres = new HashSet<>(Arrays.asList(genreToken));

            Movie movie = new Movie(movieId, title, genres);
            movies.add(movie);
        }
        
        return movies;
    }
    
}
