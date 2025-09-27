package models;

import java.util.Objects;

public class Actor {
    private String fullName;
    private String role;

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        String fmt = "%s в роли \"%s\"";
        return String.format(fmt, fullName, role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(fullName, actor.fullName) &&
                Objects.equals(role, actor.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, role);
    }
}
