package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final BiPredicate<String, Member> predicate = this::matches;

        final List<Participant> participants = new ArrayList<>();

        final Consumer<Participant> append = participants::add;

        for (Member member : members) {
            addIf(predicate, query, member, append);
        }

        return Participants.of(participants);
    }

    private void addIf(BiPredicate<String, Member> predicate, String query, Member member, Consumer<Participant> append) {
        if (predicate.test(query, member)) {
            append.accept(member.toParticipant());
        }
    }

    private boolean matches(String query, Member member) {
        return member.contains(query);
    }
}
