package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;

import static org.mockito.Mockito.*;

class DinosaurCreationActionTest {
    private static final String SOME_NAME = "Bob";
    private static final int SOME_WEIGHT = 100;
    private static final DinosaurGender SOME_GENDER = DinosaurGender.FEMALE;
    private static final DinosaurSpecies SOME_SPECIES = DinosaurSpecies.ANKYLOSAURUS;

    private Dinosaur dinosaur;
    private DinosaurFactory dinosaurFactory;
    private DinosaurRepository dinosaurRepository;
    private DinosaurCreationAction createDinosaurAction;

    @BeforeEach
    public void setUp() {
        dinosaur = mock(Dinosaur.class);
        dinosaurFactory = mock(DinosaurFactory.class);
        dinosaurRepository = mock(DinosaurRepository.class);

        createDinosaurAction = new DinosaurCreationAction(
                dinosaurRepository, dinosaurFactory, SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES
        );
    }

    @Test
    public void givenValidAction_whenExecute_thenDinosaurIsCreated() {
        createDinosaurAction.execute();

        verify(dinosaurFactory).create(SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES);
    }

    @Test
    public void givenValidAction_whenExecute_thenCreatedDinosaurIsSaved() {
        when(dinosaurFactory.create(
            anyString(), anyInt(), any(DinosaurGender.class), any(DinosaurSpecies.class)
        )).thenReturn(dinosaur);

        createDinosaurAction.execute();

        verify(dinosaurRepository).add(dinosaur);
    }

    @Test
    public void givenDuplicatedAction_whenExecuteTwoTimes_thenSecondExecuteIsIgnored() {
        when(dinosaurRepository.contains(SOME_NAME)).thenReturn(false, true);

        createDinosaurAction.execute();
        createDinosaurAction.execute();

        verify(dinosaurFactory, times(1))
            .create(anyString(), anyInt(), any(DinosaurGender.class), any(DinosaurSpecies.class));
    }
}
