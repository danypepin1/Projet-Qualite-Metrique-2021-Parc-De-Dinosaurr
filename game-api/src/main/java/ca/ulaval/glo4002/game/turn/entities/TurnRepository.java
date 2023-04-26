package ca.ulaval.glo4002.game.turn.entities;

public interface TurnRepository {
    int readTurnNumber();

    void incrementTurnNumber();

    void resetTurnNumber();
}
