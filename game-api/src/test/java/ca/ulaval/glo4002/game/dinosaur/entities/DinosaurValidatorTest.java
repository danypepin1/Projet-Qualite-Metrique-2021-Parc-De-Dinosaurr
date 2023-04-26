package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidDinosaurWeightException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidWeightChangeException;
import ca.ulaval.glo4002.game.dinosaur.infrastructure.persistence.DinosaurRepositoryInMemory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DinosaurValidatorTest {
    private static final String SOME_NAME = "Bob";
    private static final int SOME_VALID_WEIGHT = 100;
    private static final int SOME_INVALID_WEIGHT = 99;
    private static final int SOME_INVALID_WEIGHT_MODIFIER = -1;
    private static final int SOME_VALID_WEIGHT_MODIFIER = 50;

    private DinosaurRepositoryInMemory dinosaurRepositoryInMemory;
    private DinosaurValidator dinosaurValidator;
    private WeightModificationOrder weightModificationOrder;
    private Dinosaur dinosaur;
    private BabyDinosaur babyDinosaur;

    @BeforeEach
    public void setup() {
        dinosaurRepositoryInMemory = mock(DinosaurRepositoryInMemory.class);
        weightModificationOrder = mock(WeightModificationOrder.class);
        dinosaur = mock(Dinosaur.class);
        babyDinosaur = mock(BabyDinosaur.class);
        dinosaurValidator = new DinosaurValidator(dinosaurRepositoryInMemory);

        when(dinosaur.getWeight()).thenReturn(SOME_VALID_WEIGHT);
        when(weightModificationOrder.getName()).thenReturn(SOME_NAME);
    }

    @Test
    public void givenValidNameAndWeight_whenValidateCreation_thenDoesNotThrowException() {
        assertDoesNotThrow(() -> dinosaurValidator.validateCreation(SOME_NAME, SOME_VALID_WEIGHT));
    }

    @Test
    public void givenDuplicateNames_whenValidateCreation_thenThrowsDuplicateNameException() {
        when(dinosaurRepositoryInMemory.contains(SOME_NAME)).thenReturn(true);

        assertThrows(
            DuplicateNameException.class,
            () -> dinosaurValidator.validateCreation(SOME_NAME, SOME_VALID_WEIGHT)
        );
    }

    @Test
    public void givenInvalidWeight_whenValidateWeight_thenThrowsInvalidDinosaurWeightException() {
        assertThrows(
            InvalidDinosaurWeightException.class,
            () -> dinosaurValidator.validateCreation(SOME_NAME, SOME_INVALID_WEIGHT)
        );
    }

    @Test
    public void givenBabyDinosaur_whenValidateUpdateWeight_thenThrowsInvalidBabyWeightChangeException() {
        when(dinosaurRepositoryInMemory.findByName(SOME_NAME)).thenReturn(babyDinosaur);

        assertThrows(
            InvalidBabyWeightChangeException.class,
            () -> dinosaurValidator.validateWeightUpdate(weightModificationOrder)
        );
    }

    @Test
    public void givenInvalidWeightModifier_whenValidateUpdateWeight_thenThrowsInvalidWeightChangeException() {
        when(dinosaurRepositoryInMemory.findByName(SOME_NAME)).thenReturn(dinosaur);
        when(weightModificationOrder.getWeight()).thenReturn(SOME_INVALID_WEIGHT_MODIFIER);

        assertThrows(
            InvalidWeightChangeException.class,
            () -> dinosaurValidator.validateWeightUpdate(weightModificationOrder)
        );
    }

    @Test
    public void givenValidWeightModifier_whenValidateUpdateWeight_thenDoesNotThrowException() {
        when(dinosaurRepositoryInMemory.findByName(SOME_NAME)).thenReturn(dinosaur);
        when(weightModificationOrder.getWeight()).thenReturn(SOME_VALID_WEIGHT_MODIFIER);

        assertDoesNotThrow(() -> dinosaurValidator.validateWeightUpdate(weightModificationOrder));
    }
}
