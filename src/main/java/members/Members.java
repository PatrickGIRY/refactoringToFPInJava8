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
        final Predicate<Member> predicate = matches(query);

        final List<Participant> participants = new ArrayList<>();
        for (Member member : members) {
            if (predicate.test(member)) {
                participants.add(member.toParticipant());
            }
        }

        return Participants.of(participants);
    }

    private Predicate<Member> matches(String query) {
        return member -> member.startWith(query);
    }
}
