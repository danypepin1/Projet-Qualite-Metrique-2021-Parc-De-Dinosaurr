package ca.ulaval.glo4002.game.resource.api.exceptions;

public class NegativeResourceQuantityException extends RuntimeException {
    private static final String MESSAGE = "Resource quantities must be positive.";
    public NegativeResourceQuantityException() {
        super(MESSAGE);
    }
}
