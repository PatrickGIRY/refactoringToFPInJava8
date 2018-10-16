package members;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class Participants {
    static final Participants NO = new Participants(Collections.emptyList());

    private final List<Participant> participants;

    static Participants of(List<Participant> participants) {
        return participants.isEmpty() ? NO : new Participants(participants);
    }

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    boolean contains(Participant... participants) {
        return participants != null && Arrays.stream(participants)
                .allMatch(this.participants::contains);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participants that = (Participants) o;
        return Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participants);
    }

    @Override
    public String toString() {
        return "Participants{" +
                "participants=" + participants +
                '}';
    }
}
