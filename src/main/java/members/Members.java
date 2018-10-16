package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final BiPredicate<String, Member> predicate = this::matches;

        final BiConsumer<Participant, List<Participant>> append = (participant, participants) -> participants.add(participant);

        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            BiConsumer<Participant, List<Participant>> consumer = addIf(predicate, query, member, append);
            consumer.accept(member.toParticipant(), participants);
        }

        return Participants.of(participants);
    }

    private BiConsumer<Participant, List<Participant>> addIf(BiPredicate<String, Member> predicate, String query, Member member, BiConsumer<Participant, List<Participant>> append) {
        if (predicate.test(query, member)) {
            return append;
        } else {
            return (participant, participants) -> {
            };
        }
    }

    private boolean matches(String query, Member member) {
        return member.contains(query);
    }
}
