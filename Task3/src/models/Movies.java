package models;

import java.util.List;

public class Movies {
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movies=" + movies +
                '}';
    }
}
