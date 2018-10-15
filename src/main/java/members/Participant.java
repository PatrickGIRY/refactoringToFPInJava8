package members;

import java.util.Objects;

class Participant {

    private final String firstName;

    private Participant(String firstName) {
        this.firstName = firstName;
    }

    static Participant withFirstName(String firstName) {
        return new Participant(firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "firstName='" + firstName + '\'' +
                '}';
    }
}
