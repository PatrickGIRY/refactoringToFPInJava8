package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final Predicate<Member> predicate = matches(query);
        final BiFunction<List<Participant>, Participant, List<Participant>> add = (participants, participant) -> {
            participants.add(participant);
            return participants;
        };

        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            addIf(predicate, member, add).apply(participants, member.toParticipant());
        }

        return Participants.of(participants);
    }

    private BiFunction<List<Participant>, Participant, List<Participant>> addIf(Predicate<Member> predicate, Member member, BiFunction<List<Participant>, Participant, List<Participant>> add) {
        if (predicate.test(member)) {
            return add;
        } else {
            return (ps, p) -> ps;
        }
    }

    private Predicate<Member> matches(String query) {
        return member -> member.startWith(query);
    }
}
