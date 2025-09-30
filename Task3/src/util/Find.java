package util;

import models.Application;
import models.Movie;
import models.Movies;

import java.util.*;


public class Find {
    public static void movie(Movies movies) {
        boolean isFound = false;
        for (Movie movie : movies.getMovies()) {
            if (movie.getName().contains("Har")) {
                System.out.println(movie);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Фильм не найден.");
        }
    }

    public static void byActor(List<Movie> movies, String name) {
        Map<String, List<Movie>> actorListMap = new HashMap<>();
        Application.groupByActor(movies, actorListMap);
        Show.byActorOrDirector(name, actorListMap, "Фильмы с актером: ", "Актер не найден.");

        List<Movie> forSort = new ArrayList<>(actorListMap.get(name));
        Sort.byActorOrDirector(name, forSort, "Фильмы с актером: ", "Актер не найден.");
    }

    public static void byDirector(List<Movie> movies, String directorName) {
        Map<String, List<Movie>> directorListMap = new HashMap<>();
        Application.groupMoviesByDirector(movies, directorListMap);
        Show.byActorOrDirector(directorName, directorListMap, "Фильмы с режиссером: ", "Режиссер не найден.");

        List<Movie> forSort = new ArrayList<>(directorListMap.get(directorName));
        Sort.byActorOrDirector(directorName, forSort, "Фильмы с режиссером: ", "Режиссер не найден.");
    }

    public static void byYear(List<Movie> movies, int year) {
        Map<Integer, List<Movie>> yearListMap = new HashMap<>();

        Application.groupMoviesByYear(movies, yearListMap);
        Show.year(year, yearListMap);
        Sort.byYearReverse(year, yearListMap);
    }

    public static void byRole(List<Movie> movies, String name) {
        Map<String, List<String>> actorListMap = new HashMap<>();

        Application.groupMoviesAndRoleByActorName(movies, actorListMap);
        Show.role(name, actorListMap);
        Sort.byRole(name, actorListMap);
    }

    public static void all(List<Movie> movies) {
        Map<String, Set<String>> allActors = new TreeMap<>();

        Application.groupMoviesByActorsAndRole(movies, allActors);
        Show.all(allActors);
        Sort.all(allActors);
    }
}
