package ca.ulaval.glo4002.game.dinosaur.entities.exceptions;

public class InvalidDinosaurWeightException extends RuntimeException {
    private static final String MESSAGE = "The specified weight must be equal or greater than 100 kg.";
    public InvalidDinosaurWeightException() {
        super(MESSAGE);
    }
}
