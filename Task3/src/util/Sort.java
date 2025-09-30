package util;

import models.Movie;

import java.util.*;

public class Sort {
    private static void sortMovies(List<Movie> movies, Comparator<Movie> comparator, String category) {
        System.out.println(category + " (по алфавиту или нарастанию):");
        movies.sort(comparator);
        movies.forEach(System.out::println);

        System.out.println("=================================");

        System.out.println(category + " (в обратном порядке):");
        movies.sort(comparator.reversed());
        movies.forEach(System.out::println);
    }

    public static void byName(List<Movie> movies) {
        sortMovies(movies, Comparator.naturalOrder(), "Сортировка по названию");
    }

    public static void byYear(List<Movie> movies) {
        sortMovies(movies, Comparator.comparingInt(Movie::getYear), "Сортировка по году");
    }

    public static void byDirector(List<Movie> movies) {
        sortMovies(movies, Comparator.comparing(Movie::getDirector), "Сортировка по режиссёру");
    }

    public static void byActorOrDirector(String name, List<Movie> forSort, String s, String s2) {
        if (forSort != null) {
            forSort.sort(Comparator.reverseOrder());
            System.out.println(s + name + " в  обратной сортировке");
            forSort.forEach(System.out::println);
        } else {
            System.out.println(s2);
        }
    }

    public static void byYearReverse(int year, Map<Integer, List<Movie>> yearListMap) {
        List<Movie> forSort = new ArrayList<>(yearListMap.get(year));
        forSort.sort(Comparator.reverseOrder());
        System.out.println("Фильмы с годом: " + year + " в  обратной сортировке");
        forSort.forEach(System.out::println);
    }

    public static void byRole(String name, Map<String, List<String>> actorListMap) {
        List<String> forSort = new ArrayList<>(actorListMap.get(name));
        forSort.sort(Comparator.reverseOrder());
        System.out.println("Фильмы с актером: " + name + " в  обратной сортировке");
        forSort.forEach(System.out::println);
    }

    public static void all(Map<String, Set<String>> allActors) {
        System.out.println("В обратном порядке");
        Map<String, Set<String>> allActorsReversed = new TreeMap<>(Comparator.reverseOrder());
        allActorsReversed.putAll(allActors);

        allActorsReversed.forEach((actor, movies) -> {
            System.out.println("Актёр: " + actor);
            List<String> forSort = new ArrayList<>(movies);
            forSort.sort(Comparator.reverseOrder());

            forSort.forEach(System.out::println);
            System.out.println();
        });
    }
}
