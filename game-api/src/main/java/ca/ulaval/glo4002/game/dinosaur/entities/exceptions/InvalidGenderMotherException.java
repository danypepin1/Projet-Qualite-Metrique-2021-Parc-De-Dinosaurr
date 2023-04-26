package ca.ulaval.glo4002.game.dinosaur.entities.exceptions;

public class InvalidGenderMotherException extends RuntimeException {
    private static final String MESSAGE = "Mother must be a female.";
    public InvalidGenderMotherException() {
        super(MESSAGE);
    }
}
