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
            if (matches(query, member)) {
                participants.add(member.toParticipant());
            }
        }
        return Participants.of(participants);
    }

    private boolean matches(String query, Member member) {
        return member.startWith(query);
    }
}
