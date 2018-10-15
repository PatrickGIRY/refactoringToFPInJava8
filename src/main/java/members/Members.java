package members;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {

        final List<Participant> participants = Arrays.stream(members).
                filter(matches(query))
                .map(Member::toParticipant)
                .collect(Collectors.toList());

        return Participants.of(participants);
    }

    private Predicate<Member> matches(String query) {
        return member -> member.startWith(query);
    }
}
