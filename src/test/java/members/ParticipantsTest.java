package members;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Participants should")
class ParticipantsTest {

    private static final Participant BOB = Participant.withFirstName("Bob");
    private static final Participant MARC = Participant.withFirstName("Marc");
    private static final Participant PAUL = Participant.withFirstName("PAUL");

    @Test
    @DisplayName("not contains participant when there is no participant")
    void no_participant() {
        Participants participants = Participants.NO;

        assertThat(participants.contains(BOB)).isFalse();
    }

    @Test
    @DisplayName("contains one participant when the participant is in")
    void one_participant() {
        Participants participants = Participants.of(Collections.singletonList(BOB));

        assertThat(participants.contains(BOB)).isTrue();
    }

    @Test
    @DisplayName("contains all participants when all participants are in")
    void all_participants() {
        Participants participants = Participants.of(Arrays.asList(MARC, BOB));

        assertThat(participants.contains(MARC, BOB)).isTrue();
    }

    @Test
    @DisplayName("not contains all participants when not all participants are in")
    void not_all_participants() {
        Participants participants = Participants.of(Arrays.asList(MARC, BOB));

        assertThat(participants.contains(MARC, BOB, PAUL)).isFalse();
    }

}