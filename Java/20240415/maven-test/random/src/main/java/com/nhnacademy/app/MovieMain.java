package com.nhnacademy.app;

import java.util.List;

public class MovieMain {
    
    public static void main(String[] args) {
        // MovieParser movieParser1 = new BasicMovieParser();

        // try {
        //     List<Movie> movieList = movieParser1.parse("movies.csv");
            
        //     for(Movie movie : movieList) {
        //         System.out.println(movie);
        //     }
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        MovieParser movieParser2 = new ApacheCommonsCsvMovieParser();
        
        try {
            List<Movie> movieList = movieParser2.parse("movies.csv");
            
            for(Movie movie : movieList) {
                System.out.println(movie);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
