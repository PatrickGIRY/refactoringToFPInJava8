package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final BiPredicate<String, Member> predicate = this::matches;

        final Function<Participant, Consumer<List<Participant>>> append =
                participant -> participants -> participants.add(participant);

        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            Consumer<List<Participant>> consumer = addIf(predicate, query, member, append);
            consumer.accept(participants);
        }

        return Participants.of(participants);
    }

    private Consumer<List<Participant>> addIf(BiPredicate<String, Member> predicate, String query, Member member, Function<Participant, Consumer<List<Participant>>> append) {
        if (predicate.test(query, member)) {
            return append.apply(member.toParticipant());
        } else {
            return (participants) -> {
            };
        }
    }

    private boolean matches(String query, Member member) {
        return member.contains(query);
    }
}
