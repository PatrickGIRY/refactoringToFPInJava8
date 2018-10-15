package members;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

class Members {
    private final Member[] members;

    Members(Member... members) {
        this.members = members;
    }

    Participants findParticipantsByFirstName(String query) {
        final Predicate<Member> predicate = member -> matches(query, member);
        final List<Participant> participants = new ArrayList<>();
        final Consumer<Participant> add = participants::add;

        for (Member member : members) {
            addIf(predicate, member, add);
        }

        return Participants.of(participants);
    }

    // Side effect
    private void addIf(Predicate<Member> predicate, Member member, Consumer<Participant> add) {
        if (predicate.test(member)) {
            add.accept(member.toParticipant());
        }
    }

    private boolean matches(String query, Member member) {
        return member.startWith(query);
    }
}
