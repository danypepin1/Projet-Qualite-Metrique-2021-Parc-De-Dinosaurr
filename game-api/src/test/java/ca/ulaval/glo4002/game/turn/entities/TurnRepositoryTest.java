package ca.ulaval.glo4002.game.turn.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.turn.infrastructure.persistence.TurnRepositoryInMemory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TurnRepositoryTest {
    private static final int INITIAL_TURN_NUMBER = 1;

    private TurnRepository turnRepository;

    @BeforeEach
    public void setup() {
        turnRepository = new TurnRepositoryInMemory();
    }

    @Test
    public void givenInitialTurnNumber_whenReadingTurnNumber_thenInitialTurnNumberIsReturned() {
        int turnNumber = turnRepository.readTurnNumber();

        assertEquals(INITIAL_TURN_NUMBER, turnNumber);
    }

    @Test
    public void givenInitialTurnNumber_whenIncrementingTurnNumber_thenTurnNumberIsIncremented() {
        turnRepository.incrementTurnNumber();

        int expectedTurnNumber = INITIAL_TURN_NUMBER + 1;
        assertEquals(expectedTurnNumber, turnRepository.readTurnNumber());
    }

    @Test
    public void givenSecondTurnNumber_whenResettingTurnNumber_thenTurnNumberIsReset() {
        turnRepository.incrementTurnNumber();

        turnRepository.resetTurnNumber();

        assertEquals(INITIAL_TURN_NUMBER, turnRepository.readTurnNumber());
    }
}
