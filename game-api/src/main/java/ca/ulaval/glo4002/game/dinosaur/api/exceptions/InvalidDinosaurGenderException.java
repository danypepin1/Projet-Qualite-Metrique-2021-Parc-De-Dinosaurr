package ca.ulaval.glo4002.game.dinosaur.api.exceptions;

public class InvalidDinosaurGenderException extends RuntimeException {
    private static final String MESSAGE = "The specified gender must be \"m\" or \"f\".";
    public InvalidDinosaurGenderException() {
        super(MESSAGE);
    }
}
