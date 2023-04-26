package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurCreationActionFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender.MALE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.ANKYLOSAURUS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DinosaurCreationActionFactoryTest {
    private static final String SOME_NAME = "Bob";
    private static final int SOME_WEIGHT = 150;
    private static final DinosaurGender SOME_GENDER = MALE;
    private static final DinosaurSpecies SOME_SPECIES = ANKYLOSAURUS;

    private DinosaurValidator dinosaurValidator;
    private DinosaurCreationActionFactory dinosaurCreationActionFactory;
    private DinosaurRepository dinosaurRepository;
    private DinosaurFactory dinosaurFactory;

    @BeforeEach
    public void setup() {
        dinosaurValidator = mock(DinosaurValidator.class);
        dinosaurCreationActionFactory = new DinosaurCreationActionFactory(dinosaurValidator);
        dinosaurRepository = mock(DinosaurRepository.class);
        dinosaurFactory = mock(DinosaurFactory.class);
    }

    @Test
    public void givenFactory_whenCreate_thenValidateFromDinosaurValidatorIsCalled() {
        dinosaurCreationActionFactory.create(
                dinosaurRepository, dinosaurFactory, SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES
        );

        verify(dinosaurValidator).validateCreation(SOME_NAME, SOME_WEIGHT);
    }

    @Test
    public void givenFactory_whenCreate_thenReturnCorrectDinosaurCreationAction() {
        DinosaurCreationAction expectedAction = new DinosaurCreationAction(
            dinosaurRepository, dinosaurFactory, SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES
        );

        DinosaurCreationAction createdAction = dinosaurCreationActionFactory.create(
                dinosaurRepository, dinosaurFactory, SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES
        );

        assertEquals(expectedAction, createdAction);
    }
}
