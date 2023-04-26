package ca.ulaval.glo4002.game.dinosaur.entities.exceptions;

public class InvalidWeightChangeException extends RuntimeException {
    private static final String MESSAGE =
        "The specified weight loss must not make the dinosaur weight less than 100 kg.";
    public InvalidWeightChangeException() {
        super(MESSAGE);
    }
}
