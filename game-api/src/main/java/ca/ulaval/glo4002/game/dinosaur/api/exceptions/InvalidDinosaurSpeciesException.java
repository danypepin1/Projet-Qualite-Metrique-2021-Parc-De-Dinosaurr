package ca.ulaval.glo4002.game.dinosaur.api.exceptions;

public class InvalidDinosaurSpeciesException extends RuntimeException {
    private static final String MESSAGE = "The specified species is not supported.";
    public InvalidDinosaurSpeciesException() {
        super(MESSAGE);
    }
}
