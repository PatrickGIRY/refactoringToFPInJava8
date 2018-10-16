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
            Consumer<Participant> consumer = addIf(predicate, query, member, append);
            consumer.accept(member.toParticipant());
        }

        return Participants.of(participants);
    }

    private Consumer<Participant> addIf(BiPredicate<String, Member> predicate, String query, Member member, Consumer<Participant> append) {
        if (predicate.test(query, member)) {
            return append;
        } else {
            return participant -> {
            };
        }
    }

    private boolean matches(String query, Member member) {
        return member.contains(query);
    }
}
