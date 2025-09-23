package models;

public class Director implements Comparable<Director> {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(Director o) {
        return this.fullName.compareTo(o.fullName);
    }
}
