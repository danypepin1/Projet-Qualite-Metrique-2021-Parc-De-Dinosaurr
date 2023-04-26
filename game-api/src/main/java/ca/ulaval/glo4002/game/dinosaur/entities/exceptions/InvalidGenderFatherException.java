package ca.ulaval.glo4002.game.dinosaur.entities.exceptions;

public class InvalidGenderFatherException extends RuntimeException {
    private static final String MESSAGE = "Father must be a male.";
    public InvalidGenderFatherException() {
        super(MESSAGE);
    }
}
