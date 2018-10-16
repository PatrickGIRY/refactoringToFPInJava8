package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final BiPredicate<String, Member> predicate = this::matches;

        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            addIf(predicate, query, member, participants);
        }
        return Participants.of(participants);
    }

    private void addIf(BiPredicate<String, Member> predicate, String query, Member member, List<Participant> participants) {
        if (predicate.test(query, member)) {
            participants.add(member.toParticipant());
        }
    }

    private boolean matches(String query, Member member) {
        return member.contains(query);
    }
}
