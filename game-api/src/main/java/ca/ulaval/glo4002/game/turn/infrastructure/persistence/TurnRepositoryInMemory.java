package ca.ulaval.glo4002.game.turn.infrastructure.persistence;

import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

public class TurnRepositoryInMemory implements TurnRepository {
    private static final int INITIAL_TURN_NUMBER = 1;

    private int turnNumber = INITIAL_TURN_NUMBER;

    @Override
    public int readTurnNumber() {
        return turnNumber;
    }

    @Override
    public void incrementTurnNumber() {
        this.turnNumber += 1;
    }

    @Override
    public void resetTurnNumber() {
        this.turnNumber = INITIAL_TURN_NUMBER;
    }
}
