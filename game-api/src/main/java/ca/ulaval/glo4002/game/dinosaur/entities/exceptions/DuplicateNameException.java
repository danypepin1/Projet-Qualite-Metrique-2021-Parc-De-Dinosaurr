package ca.ulaval.glo4002.game.dinosaur.entities.exceptions;

public class DuplicateNameException extends RuntimeException {
    private static final String MESSAGE = "The specified name already exists and must be unique.";
    public DuplicateNameException() {
        super(MESSAGE);
    }
}
