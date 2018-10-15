package members;

import java.util.ArrayList;
import java.util.List;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            addIfMatches(query, member, participants);
        }
        return Participants.of(participants);
    }

    // Side effect
    private void addIfMatches(String query, Member member, List<Participant> participants) {
        if (matches(query, member)) {
            participants.add(member.toParticipant());
        }
    }

    private boolean matches(String query, Member member) {
        return member.startWith(query);
    }
}
