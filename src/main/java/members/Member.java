package members;

import java.util.Objects;

class Member {
    private final String firstName;

    private Member(String firstName) {
        this.firstName = firstName;
    }

    static Member withFirstName(String firstName) {
        return new Member(firstName);
    }

    boolean contains(String query) {
        return firstName.contains(query);
    }

    Participant toParticipant() {
        return Participant.withFirstName(firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(firstName, member.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                '}';
    }
}
