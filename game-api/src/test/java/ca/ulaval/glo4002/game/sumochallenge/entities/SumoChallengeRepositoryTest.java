package ca.ulaval.glo4002.game.sumochallenge.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.sumochallenge.infrastructure.SumoChallengeRepositoryInMemory;

import static org.junit.jupiter.api.Assertions.*;

class SumoChallengeRepositoryTest {
    private static final String SOME_CHALLENGER_NAME = "Bob";
    private static final String SOME_CHALLENGEE_NAME = "Mary";
    private static final int SOME_TURN_NUMBER = 42;

    private SumoChallenge sumoChallenge;
    private SumoChallengeRepository sumoChallengeRepository;

    @BeforeEach
    public void setup() {
        sumoChallenge = new SumoChallenge(SOME_CHALLENGER_NAME, SOME_CHALLENGEE_NAME, SOME_TURN_NUMBER);
        sumoChallengeRepository = new SumoChallengeRepositoryInMemory();
    }

    @Test
    public void givenChallenge_whenSave_thenChallengeIsSaved() {
        sumoChallengeRepository.add(sumoChallenge);

        assertEquals(sumoChallenge, sumoChallengeRepository.readAll().get(0));
    }

    @Test
    public void givenChallenge_whenClear_thenChallengeIsCleared() {
        sumoChallengeRepository.add(sumoChallenge);

        sumoChallengeRepository.clear();

        assertTrue(sumoChallengeRepository.readAll().isEmpty());
    }
}
