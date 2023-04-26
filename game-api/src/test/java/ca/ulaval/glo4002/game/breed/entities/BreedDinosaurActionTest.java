package ca.ulaval.glo4002.game.breed.entities;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

import static org.mockito.Mockito.*;

class BreedDinosaurActionTest {
    private static final String SOME_BABY_NAME = "Henry";
    private static final String SOME_FATHER_NAME = "Alain";
    private static final String SOME_MOTHER_NAME = "Linda";

    private Dinosaur babyDinosaur;
    private Dinosaur fatherDinosaur;
    private Dinosaur motherDinosaur;
    private DinosaurRepository dinosaurRepository;
    private BreedDinosaurAction breedDinosaurAction;
    private Veterinary veterinary;

    @BeforeEach
    public void setup() {
        fatherDinosaur = mock(Dinosaur.class);
        motherDinosaur = mock(Dinosaur.class);
        babyDinosaur = mock(Dinosaur.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        veterinary = mock(Veterinary.class);

        BreedOrder breedOrder = mock(BreedOrder.class);
        when(breedOrder.getBabyName()).thenReturn(SOME_BABY_NAME);
        when(breedOrder.getFatherName()).thenReturn(SOME_FATHER_NAME);
        when(breedOrder.getMotherName()).thenReturn(SOME_MOTHER_NAME);

        breedDinosaurAction = new BreedDinosaurAction(veterinary, dinosaurRepository, breedOrder);

        when(dinosaurRepository.findByName(SOME_FATHER_NAME)).thenReturn(fatherDinosaur);
        when(dinosaurRepository.findByName(SOME_MOTHER_NAME)).thenReturn(motherDinosaur);
    }

    @Test
    public void givenBreedAction_whenExecute_thenBreedFromVeterinaryIsCalled() {
        breedDinosaurAction.execute();

        verify(veterinary).breed(SOME_BABY_NAME, fatherDinosaur, motherDinosaur);
    }

    @Test
    public void givenIncompatibleParents_whenExecute_thenSaveFromDinosaurRepositoryIsNotCalled() {
        when(veterinary.breed(anyString(), any(),any())).thenReturn(Optional.empty());

        breedDinosaurAction.execute();

        verify(dinosaurRepository, never()).add(any());
    }

    @Test
    public void givenBreedAction_whenExecute_thenSaveFromDinosaurRepositoryIsCalled() {
        when(veterinary.breed(anyString(), any(),any())).thenReturn(Optional.ofNullable(babyDinosaur));

        breedDinosaurAction.execute();

        verify(dinosaurRepository).add(babyDinosaur);
    }

    @Test
    public void givenBreedAction_whenExecute_thenReadByNameFromDinosaurRepositoryIsCalledForTheFather() {
        when(veterinary.breed(anyString(), any(),any())).thenReturn(Optional.ofNullable(babyDinosaur));

        breedDinosaurAction.execute();

        verify(dinosaurRepository).findByName(SOME_FATHER_NAME);
    }

    @Test
    public void givenBreedAction_whenExecute_thenReadByNameFromDinosaurRepositoryIsCalledForTheMother() {
        when(veterinary.breed(anyString(), any(),any())).thenReturn(Optional.ofNullable(babyDinosaur));

        breedDinosaurAction.execute();

        verify(dinosaurRepository).findByName(SOME_MOTHER_NAME);
    }

    @Test
    public void givenBreedActionForAnExistingBabyName_whenExecute_thenSaveFromDinosaurRepositoryIsNotCalled() {
        when(dinosaurRepository.contains(SOME_BABY_NAME)).thenReturn(true);

        breedDinosaurAction.execute();

        verify(dinosaurRepository, never()).add(any());
    }
}
