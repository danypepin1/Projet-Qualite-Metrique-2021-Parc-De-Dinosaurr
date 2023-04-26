package ca.ulaval.glo4002.game.dinosaur.entities.exceptions;

public class DinosaurNotFoundException extends RuntimeException {
    private static final String MESSAGE = "The specified name does not exist.";
    public DinosaurNotFoundException() {
        super(MESSAGE);
    }
}
