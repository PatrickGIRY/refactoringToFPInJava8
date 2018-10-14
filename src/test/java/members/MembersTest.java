package members;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("The search for participants by first name in the list of members of the meetup should")
class MembersTest {

    @Test
    @DisplayName("Return no participant where there is no member found with the query")
    void no_participant() {
        Members members = new Members(
                Member.withFirstName("Christian"),
                Member.withFirstName("Christophe"),
                Member.withFirstName("Sébastien"),
                Member.withFirstName("Xavier"),
                Member.withFirstName("Arnaud"),
                Member.withFirstName("Patrick"),
                Member.withFirstName("Emmanuel"),
                Member.withFirstName("Karine"),
                Member.withFirstName("Benoit")
        );

        Participants participants = members.findParticipantsByFirstName("Paul");

        assertThat(participants).isEqualTo(Participants.NO);
    }
}
