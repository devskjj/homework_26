package models;

import java.util.List;

public class Movie implements Comparable<Movie> {
    private String name;
    private int year;
    private String description;
    private Director director;
    private List<Actor> cast;

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public Director getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Фильм: " +
                name + "\n" +
                "Год выпуска: " + year + "\n" +
                "Формат: " + description + "\n" +
                "Режиссер: " + director + "\n" +
                "Актерский состав: " + cast + "\n" +
                "-----------------";
    }

    @Override
    public int compareTo(Movie o) {
        return this.getName().compareToIgnoreCase(o.name);
    }
}
