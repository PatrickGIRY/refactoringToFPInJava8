package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final Predicate<Member> predicate = matches(query);

        final Function<Participant, Consumer<List<Participant>>> append =
                participant -> participants -> participants.add(participant);

        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            Consumer<List<Participant>> consumer = addIf(predicate, member, append);
            consumer.accept(participants);
        }

        return Participants.of(participants);
    }

    private Consumer<List<Participant>> addIf(Predicate<Member> predicate, Member member, Function<Participant, Consumer<List<Participant>>> append) {
        if (predicate.test(member)) {
            return append.apply(member.toParticipant());
        } else {
            return (participants) -> {
            };
        }
    }

    private Predicate<Member> matches(String query) {
        return member -> member.contains(query);
    }
}
