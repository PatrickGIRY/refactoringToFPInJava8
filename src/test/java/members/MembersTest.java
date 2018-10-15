package members;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("The search for participants by first name in the list of members of the meetup should")
class MembersTest {

    private Members members;

    @BeforeEach
    void setUp() {
        members = new Members(
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
    }

    @Test
    @DisplayName("Return no participant where there is no member found with the query")
    void no_participant() {

        Participants participants = members.findParticipantsByFirstName("Paul");

        assertThat(participants).isEqualTo(Participants.NO);
    }

    @Test
    @DisplayName("Return one participant when there is a member found with the query")
    void one_participant() {

        Participants participants = members.findParticipantsByFirstName("Xavier");

        assertThat(participants.contains(Participant.withFirstName("Xavier")))
                .isTrue();
    }

    @Test
    @DisplayName("Return all matching participants where there are multiple members found with the query")
    void many_participants() {

        Participants participants = members.findParticipantsByFirstName("Chr");

        assertThat(participants.contains(
                Participant.withFirstName("Christian"),
                Participant.withFirstName("Christophe")
        )).isTrue();
    }
}
