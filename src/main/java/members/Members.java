package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        Predicate<Member> predicate = member -> matches(query, member);

        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            addIf(predicate, member, participants);
        }
        return Participants.of(participants);
    }

    // Side effect
    private void addIf(Predicate<Member> predicate, Member member, List<Participant> participants) {
        if (predicate.test(member)) {
            participants.add(member.toParticipant());
        }
    }

    private boolean matches(String query, Member member) {
        return member.startWith(query);
    }
}
