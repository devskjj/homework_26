package models;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Show {
    public static void byActorOrDirector(String directorName, Map<String, List<Movie>> directorListMap, String s, String s2) {
        List<Movie> moviesByDirector = directorListMap.get(directorName);
        if (moviesByDirector != null) {
            System.out.println(s + directorName);
            moviesByDirector.forEach(System.out::println);
        } else {
            System.out.println(s2);
        }
    }

    public static void year(int year, Map<Integer, List<Movie>> yearListMap) {
        List<Movie> moviesByYear = yearListMap.get(year);
        if (moviesByYear != null) {
            System.out.println("Фильмы с годом: " + year);
            moviesByYear.forEach(System.out::println);
        } else {
            System.out.println("Год не найден.");
        }
    }

    public static void all(Map<String, Set<String>> allActors) {
        System.out.println();
        System.out.println("Список всех актеров из всех фильмов с указанием их ролей, без дубликатов и в отсортированном виде: ");
        System.out.println();
        allActors.forEach((actor, roles) -> {
            System.out.println("Актёр: " + actor);
            roles.forEach(System.out::println);
            System.out.println();
        });
    }

    public static void role(String name, Map<String, List<String>> actorListMap) {
        List<String> moviesByActor = actorListMap.get(name);
        if (moviesByActor != null) {
            System.out.println("Фильмы с актером: " + name);
            moviesByActor.forEach(System.out::println);
        } else {
            System.out.println("Актер не найден.");
        }
    }
}
