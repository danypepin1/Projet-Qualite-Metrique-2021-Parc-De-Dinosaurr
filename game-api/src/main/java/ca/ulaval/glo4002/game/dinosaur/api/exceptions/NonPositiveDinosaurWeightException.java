package ca.ulaval.glo4002.game.dinosaur.api.exceptions;

public class NonPositiveDinosaurWeightException extends RuntimeException {
    private static final String MESSAGE = "The specified weight must be greater than 0.";
    public NonPositiveDinosaurWeightException() {
        super(MESSAGE);
    }
}
