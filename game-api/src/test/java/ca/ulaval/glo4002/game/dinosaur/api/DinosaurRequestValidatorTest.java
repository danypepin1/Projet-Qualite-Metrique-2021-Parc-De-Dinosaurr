package ca.ulaval.glo4002.game.dinosaur.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaur.api.exceptions.InvalidDinosaurGenderException;
import ca.ulaval.glo4002.game.dinosaur.api.exceptions.InvalidDinosaurSpeciesException;
import ca.ulaval.glo4002.game.dinosaur.api.exceptions.NonPositiveDinosaurWeightException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DinosaurRequestValidatorTest {
    private static final int SOME_VALID_WEIGHT = 152;
    private static final int SOME_NON_POSITIVE_WEIGHT = 0;
    private static final String SOME_NAME = "Bob";
    private static final String SOME_VALID_SPECIES = "Ankylosaurus";
    private static final String SOME_VALID_GENDER = "m";
    private static final String SOME_INVALID_GENDER = "male";
    private static final String SOME_INVALID_SPECIES = "Godzilla";

    private DinosaurRequest dinosaurRequest;
    private DinosaurRequestValidator dinosaurRequestValidator;

    @BeforeEach
    public void setup() {
        dinosaurRequest = new DinosaurRequest();
        dinosaurRequest.name = SOME_NAME;
        dinosaurRequest.species = SOME_VALID_SPECIES;
        dinosaurRequest.gender = SOME_VALID_GENDER;
        dinosaurRequest.weight = SOME_VALID_WEIGHT;

        dinosaurRequestValidator = new DinosaurRequestValidator();
    }

    @Test
    public void givenValidRequest_whenValidate_thenDoesNotThrow() {
        assertDoesNotThrow(() -> dinosaurRequestValidator.validate(dinosaurRequest));
    }

    @Test
    public void givenNonPositiveDinosaurWeight_whenValidate_thenThrowsInvalidDinosaurWeightException() {
        dinosaurRequest.weight = SOME_NON_POSITIVE_WEIGHT;

        assertThrows(
            NonPositiveDinosaurWeightException.class,
            () -> dinosaurRequestValidator.validate(dinosaurRequest)
        );
    }

    @Test
    public void givenInvalidSpecies_whenValidate_thenThrowsInvalidDinosaurSpeciesException() {
        dinosaurRequest.species = SOME_INVALID_SPECIES;

        assertThrows(
            InvalidDinosaurSpeciesException.class,
            () -> dinosaurRequestValidator.validate(dinosaurRequest)
        );
    }

    @Test
    public void givenInvalidGender_whenValidate_thenThrowsInvalidDinosaurGenderException() {
        dinosaurRequest.gender = SOME_INVALID_GENDER;

        assertThrows(
            InvalidDinosaurGenderException.class,
            () -> dinosaurRequestValidator.validate(dinosaurRequest)
        );
    }
}
