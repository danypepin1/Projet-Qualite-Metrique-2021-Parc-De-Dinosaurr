package ca.ulaval.glo4002.game.dinosaur.entities.exceptions;

public class InvalidBabyWeightChangeException extends RuntimeException {
    private static final String MESSAGE = "The weight of a baby dinosaur can not be changed.";
    public InvalidBabyWeightChangeException() {
        super(MESSAGE);
    }
}
