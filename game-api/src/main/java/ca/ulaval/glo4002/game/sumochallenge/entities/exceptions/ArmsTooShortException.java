package ca.ulaval.glo4002.game.sumochallenge.entities.exceptions;

public class ArmsTooShortException extends RuntimeException {
    private static final String MESSAGE = "Tyrannosaurus Rex can't participate.";
    public ArmsTooShortException() {
        super(MESSAGE);
    }
}
