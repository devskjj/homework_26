package models;

import com.google.gson.Gson;
import util.Find;
import util.Sort;

import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Application {
    public static void runApplication() {
        Movies movies = readJson();
        printList(movies.getMovies());

        List<Movie> copy = new ArrayList<>(movies.getMovies());

        System.out.println();
        System.out.println("Метод выводящий фильм по названию частично или полностью: ");
        Find.movie(movies);

        System.out.println();
        Sort.byYear(copy);

        System.out.println();
        Sort.byName(copy);

        System.out.println();
        Sort.byDirector(copy);

        System.out.println();
        System.out.println("Первоначальная коллекция");
        printList(movies.getMovies());

        Find.byActor(copy, "Orlando Bloom");
        Find.byDirector(copy, "Peter Jackson");
        Find.byYear(copy, 2012);
        Find.byRole(copy, "Orlando Bloom");
        Find.all(copy);
    }

    private static Movies readJson() {
        Movies movies = null;
        try {
            String path = new String((Files.readAllBytes(Paths.get("Task3/src/data/movies.json"))));
            Gson gson = new Gson();
            movies = gson.fromJson(path, Movies.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static void groupByActor(List<Movie> movies, Map<String, List<Movie>> actorListMap) {
        movies.forEach(movie -> movie.getCast().forEach(actor -> {
            actorListMap.computeIfAbsent(actor.getFullName(), var -> new ArrayList<>()).add(movie);
        }));
    }

    public static void groupMoviesByDirector(List<Movie> movies, Map<String, List<Movie>> directorListMap) {
        movies.forEach(movie -> directorListMap.computeIfAbsent(movie.getDirector().getFullName(), var -> new ArrayList<>()).add(movie));
    }

    public static void groupMoviesByYear(List<Movie> movies, Map<Integer, List<Movie>> yearListMap) {
        movies.forEach(movie -> yearListMap.computeIfAbsent(movie.getYear(), var -> new ArrayList<>()).add(movie));
    }

    public static void groupMoviesAndRoleByActorName(List<Movie> movies, Map<String, List<String>> actorListMap) {
        movies.forEach(movie -> movie.getCast().forEach(actor -> {
            actorListMap.computeIfAbsent(actor.getFullName(), var -> new ArrayList<>()).add(movie.getName() + " - в роли " + actor.getRole());
        }));
    }

    public static void groupMoviesByActorsAndRole(List<Movie> movies, Map<String, Set<String>> allActors) {
        movies.forEach(movie -> movie.getCast().forEach(actor -> {
            allActors.computeIfAbsent(actor.getFullName(), var -> new TreeSet<>()).add(movie.getName() + " -- в роли: " + actor.getRole());
        }));
    }

    private static void printList(List<Movie> movies) {
        movies.forEach(System.out::println);
    }
}