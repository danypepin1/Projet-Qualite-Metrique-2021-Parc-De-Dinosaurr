package ca.ulaval.glo4002.game.breed.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DinosaurNotFoundException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidGenderFatherException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidGenderMotherException;
import ca.ulaval.glo4002.game.dinosaur.infrastructure.persistence.DinosaurRepositoryInMemory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BreedValidatorTest {
    private static final String SOME_BABY_NAME = "Bob";
    private static final String SOME_FATHER_NAME = "Roger";
    private static final String SOME_MOTHER_NAME = "Ginette";

    private DinosaurRepositoryInMemory dinosaurRepositoryInMemory;
    private BreedValidator breedValidator;
    private Dinosaur maleDinosaur;
    private Dinosaur femaleDinosaur;
    private BreedOrder breedOrder;

    @BeforeEach
    public void setup() {
        breedOrder = mock(BreedOrder.class);
        when(breedOrder.getBabyName()).thenReturn(SOME_BABY_NAME);
        when(breedOrder.getFatherName()).thenReturn(SOME_FATHER_NAME);
        when(breedOrder.getMotherName()).thenReturn(SOME_MOTHER_NAME);

        dinosaurRepositoryInMemory = mock(DinosaurRepositoryInMemory.class);
        breedValidator = new BreedValidator(dinosaurRepositoryInMemory);

        maleDinosaur = mock(Dinosaur.class);
        when(maleDinosaur.isMale()).thenReturn(true);
        when(maleDinosaur.isFemale()).thenReturn(false);

        femaleDinosaur = mock(Dinosaur.class);
        when(femaleDinosaur.isFemale()).thenReturn(true);
        when(femaleDinosaur.isMale()).thenReturn(false);

        when(dinosaurRepositoryInMemory.contains(SOME_MOTHER_NAME)).thenReturn(true);
        when(dinosaurRepositoryInMemory.contains(SOME_FATHER_NAME)).thenReturn(true);
        when(dinosaurRepositoryInMemory.findByName(SOME_FATHER_NAME)).thenReturn(maleDinosaur);
    }

    @Test
    public void givenDuplicateName_whenValidateBreed_thenThrowsDuplicateNameException() {
        when(dinosaurRepositoryInMemory.contains(SOME_BABY_NAME)).thenReturn(true);

        assertThrows(
            DuplicateNameException.class,
            () -> breedValidator.validateBreed(breedOrder)
        );
    }

    @Test
    public void givenNotExistentParentName_whenValidateBreed_thenThrowsDinosaurNotFoundException() {
        when(dinosaurRepositoryInMemory.contains(SOME_MOTHER_NAME)).thenReturn(false);

        assertThrows(
            DinosaurNotFoundException.class,
            () -> breedValidator.validateBreed(breedOrder)
        );
    }

    @Test
    public void givenInvalidFatherGender_whenValidateBreed_thenThrowsInvalidGenderFatherException() {
        when(dinosaurRepositoryInMemory.findByName(SOME_FATHER_NAME)).thenReturn(femaleDinosaur);

        assertThrows(
            InvalidGenderFatherException.class,
            () -> breedValidator.validateBreed(breedOrder)
        );
    }

    @Test
    public void givenInvalidMotherGender_whenValidateBreed_thenThrowsInvalidGenderMotherException() {
        when(dinosaurRepositoryInMemory.findByName(SOME_MOTHER_NAME)).thenReturn(maleDinosaur);

        assertThrows(
            InvalidGenderMotherException.class,
            () -> breedValidator.validateBreed(breedOrder)
        );
    }

    @Test
    public void givenValidBabyDinosaur_whenValidateBreed_thenDoesNotThrowException() {
        when(dinosaurRepositoryInMemory.findByName(SOME_MOTHER_NAME)).thenReturn(femaleDinosaur);

        assertDoesNotThrow(() -> breedValidator.validateBreed(breedOrder));
    }
}
