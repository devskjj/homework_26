import com.google.gson.Gson;

import models.Actor;
import models.Movie;
import models.Movies;

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
        findMovie(movies);

        System.out.println();
        sortByYear(copy);

        System.out.println();
        sortByMovieName(copy);

        System.out.println();
        sortByDirector(copy);

        System.out.println();
        System.out.println("Первоначальная коллекция");
        printList(movies.getMovies());

        findMovieByActorName(copy, "Orlando Bloom");
        findMovieByDirectorName(copy, "Peter Jackson");
        findMovieByYear(copy, 2012);
        findMovieAndRoleByActorName(copy, "Orlando Bloom");
        findAllActorsAndRoleFromAllMovies(copy);
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

    private static void findMovieByActorName(List<Movie> movies, String name) {
        Map<String, List<Movie>> actorListMap = new HashMap<>();
        groupMovieByActorName(movies, actorListMap);
        showMoviesByActorOrDirector(name, actorListMap, "Фильмы с актером: ", "Актер не найден.");

        List<Movie> forSort = new ArrayList<>(actorListMap.get(name));
        sortForActorOrDirector(name, forSort, "Фильмы с актером: ", "Актер не найден.");
    }

    private static void groupMovieByActorName(List<Movie> movies, Map<String, List<Movie>> actorListMap) {
        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                if (!actorListMap.containsKey(actor.getFullName())) {
                    actorListMap.put(actor.getFullName(), new ArrayList<>());
                }
                actorListMap.get(actor.getFullName()).add(movie);
            }
        }
    }

    private static void sortForActorOrDirector(String name, List<Movie> forSort, String s, String s2) {
        if (forSort != null) {
            forSort.sort(Comparator.reverseOrder());
            System.out.println(s + name + " в  обратной сортировке");
            forSort.forEach(System.out::println);
        } else {
            System.out.println(s2);
        }
    }

    private static void findMovieByDirectorName(List<Movie> movies, String directorName) {
        Map<String, List<Movie>> directorListMap = new HashMap<>();
        groupMoviesByDirector(movies, directorListMap);
        showMoviesByActorOrDirector(directorName, directorListMap, "Фильмы с режиссером: ", "Режиссер не найден.");

        List<Movie> forSort = new ArrayList<>(directorListMap.get(directorName));
        sortForActorOrDirector(directorName, forSort, "Фильмы с режиссером: ", "Режиссер не найден.");
    }

    private static void groupMoviesByDirector(List<Movie> movies, Map<String, List<Movie>> directorListMap) {
        movies.forEach(movie -> directorListMap.computeIfAbsent(movie.getDirector().getFullName(), var -> new ArrayList<>()).add(movie));
    }

    private static void showMoviesByActorOrDirector(String directorName, Map<String, List<Movie>> directorListMap, String s, String s2) {
        List<Movie> moviesByDirector = directorListMap.get(directorName);
        if (moviesByDirector != null) {
            System.out.println(s + directorName);
            moviesByDirector.forEach(System.out::println);
        } else {
            System.out.println(s2);
        }
    }

    private static void findMovieByYear(List<Movie> movies, int year) {
        Map<Integer, List<Movie>> yearListMap = new HashMap<>();

        groupMoviesByYear(movies, yearListMap);
        showMoviesByYear(year, yearListMap);
        sortMoviesByYear(year, yearListMap);
    }

    private static void showMoviesByYear(int year, Map<Integer, List<Movie>> yearListMap) {
        List<Movie> moviesByYear = yearListMap.get(year);
        if (moviesByYear != null) {
            System.out.println("Фильмы с годом: " + year);
            moviesByYear.forEach(System.out::println);
        } else {
            System.out.println("Год не найден.");
        }
    }

    private static void groupMoviesByYear(List<Movie> movies, Map<Integer, List<Movie>> yearListMap) {
        for (Movie movie : movies) {
            int yearMovie = movie.getYear();
            if (!yearListMap.containsKey(yearMovie)) {
                yearListMap.put(yearMovie, new ArrayList<>());
            }
            yearListMap.get(yearMovie).add(movie);
        }
    }

    private static void sortMoviesByYear(int year, Map<Integer, List<Movie>> yearListMap) {
        List<Movie> forSort = new ArrayList<>(yearListMap.get(year));
        forSort.sort(Comparator.reverseOrder());
        System.out.println("Фильмы с годом: " + year + " в  обратной сортировке");
        forSort.forEach(System.out::println);
    }

    private static void findMovieAndRoleByActorName(List<Movie> movies, String name) {
        Map<String, List<String>> actorListMap = new HashMap<>();

        groupMoviesAndRoleByActorName(movies, actorListMap);
        showMovieAndRoleByActorName(name, actorListMap);
        sortForMovieAndRoleByActorName(name, actorListMap);
    }

    private static void showMovieAndRoleByActorName(String name, Map<String, List<String>> actorListMap) {
        List<String> moviesByActor = actorListMap.get(name);
        if (moviesByActor != null) {
            System.out.println("Фильмы с актером: " + name);
            moviesByActor.forEach(System.out::println);
        } else {
            System.out.println("Актер не найден.");
        }
    }

    private static void sortForMovieAndRoleByActorName(String name, Map<String, List<String>> actorListMap) {
        List<String> forSort = new ArrayList<>(actorListMap.get(name));
        forSort.sort(Comparator.reverseOrder());
        System.out.println("Фильмы с актером: " + name + " в  обратной сортировке");
        forSort.forEach(System.out::println);
    }

    private static void groupMoviesAndRoleByActorName(List<Movie> movies, Map<String, List<String>> actorListMap) {
        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                String actorName = actor.getFullName();
                String actorRole = actor.getRole();
                String movieAndRole = movie.getName() + " -- в роли: " + actorRole;

                if (!actorListMap.containsKey(actorName)) {
                    actorListMap.put(actorName, new ArrayList<>());
                }
                actorListMap.get(actorName).add(movieAndRole);
            }
        }
    }

    private static void findAllActorsAndRoleFromAllMovies(List<Movie> movies) {
        Map<String, Set<String>> allActors = new TreeMap<>();

        groupMoviesByActorsAndRole(movies, allActors);
        showActorsAndRoleFromAllMovies(allActors);
        sortForTreeMapReversed(allActors);
    }

    private static void showActorsAndRoleFromAllMovies(Map<String, Set<String>> allActors) {
        System.out.println();
        System.out.println("Список всех актеров из всех фильмов с указанием их ролей, без дубликатов и в отсортированном виде: ");
        System.out.println();
        for (Map.Entry<String, Set<String>> entry : allActors.entrySet()) {
            System.out.println("Актёр: " + entry.getKey());
            entry.getValue().forEach(System.out::println);
            System.out.println();
        }
    }

    private static void groupMoviesByActorsAndRole(List<Movie> movies, Map<String, Set<String>> allActors) {
        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                String roleInfo = movie.getName() + " -- в роли: " + actor.getRole();
                if (!allActors.containsKey(actor.getFullName())) {
                    allActors.put(actor.getFullName(), new TreeSet<>());
                }
                allActors.get(actor.getFullName()).add(roleInfo);
            }
        }
    }

    private static void sortForTreeMapReversed(Map<String, Set<String>> allActors) {
        System.out.println("В обратном порядке");
        Map<String, Set<String>> allActorsReversed = new TreeMap<>(Comparator.reverseOrder());
        allActorsReversed.putAll(allActors);

        for (Map.Entry<String, Set<String>> entry : allActorsReversed.entrySet()) {
            System.out.println("Актёр: " + entry.getKey());
            List<String> forSort = new ArrayList<>(entry.getValue());
            forSort.sort(Comparator.reverseOrder());
            forSort.forEach(System.out::println);
            System.out.println();
        }
    }

    private static void findMovie(Movies movies) {
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

    private static void sortMovies(List<Movie> movies, Comparator<Movie> comparator, String category) {
        System.out.println(category + " (по алфавиту или нарастанию):");
        movies.sort(comparator);
        movies.forEach(System.out::println);

        System.out.println("=================================");

        System.out.println(category + " (в обратном порядке):");
        movies.sort(comparator.reversed());
        movies.forEach(System.out::println);
    }

    private static void sortByYear(List<Movie> movies) {
        sortMovies(movies, Comparator.comparingInt(Movie::getYear), "Сортировка по году");
    }

    private static void sortByMovieName(List<Movie> movies) {
        sortMovies(movies, Comparator.naturalOrder(), "Сортировка по названию");
    }

    private static void sortByDirector(List<Movie> movies) {
        sortMovies(movies, Comparator.comparing(Movie::getDirector), "Сортировка по режиссёру");
    }

    private static void printList(List<Movie> movies) {
        movies.forEach(System.out::println);
    }
}
