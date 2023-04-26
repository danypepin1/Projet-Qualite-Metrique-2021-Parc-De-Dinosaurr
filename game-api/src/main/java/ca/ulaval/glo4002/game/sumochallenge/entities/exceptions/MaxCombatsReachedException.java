package ca.ulaval.glo4002.game.sumochallenge.entities.exceptions;

public class MaxCombatsReachedException extends RuntimeException {
    private static final String MESSAGE = "Max number of combats has been reached.";
    public MaxCombatsReachedException() {
        super(MESSAGE);
    }
}
