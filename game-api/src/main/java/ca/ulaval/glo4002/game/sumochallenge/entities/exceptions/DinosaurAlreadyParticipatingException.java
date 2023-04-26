package ca.ulaval.glo4002.game.sumochallenge.entities.exceptions;

public class DinosaurAlreadyParticipatingException extends RuntimeException {
    private static final String MESSAGE = "Dinosaur is already participating.";
    public DinosaurAlreadyParticipatingException() {
        super(MESSAGE);
    }
}
