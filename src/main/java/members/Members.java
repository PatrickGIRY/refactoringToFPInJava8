package members;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        return Participants.of(Arrays.stream(members)
                .filter(matches(query))
                .map(Member::toParticipant)
                .collect(Collectors.toList()));
    }

    private Predicate<Member> matches(String query) {
        return member -> member.contains(query);
    }
}
