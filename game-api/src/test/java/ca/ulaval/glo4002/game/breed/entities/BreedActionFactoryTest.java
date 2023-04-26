package ca.ulaval.glo4002.game.breed.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BreedActionFactoryTest {
    private Veterinary veterinary;
    private DinosaurRepository dinosaurRepository;
    private BreedActionFactory breedActionFactory;
    private BreedOrder breedOrder;

    @BeforeEach
    public void setup() {
        breedActionFactory = new BreedActionFactory(mock(BreedValidator.class));
        dinosaurRepository = mock(DinosaurRepository.class);
        veterinary = mock(Veterinary.class);
        breedOrder = mock(BreedOrder.class);
    }

    @Test
    public void givenFactory_whenCreate_thenReturnCorrectBreedDinosaurAction() {
        BreedDinosaurAction expectedAction = new BreedDinosaurAction(veterinary, dinosaurRepository, breedOrder);

        BreedDinosaurAction breedDinosaurAction = breedActionFactory.create(veterinary, dinosaurRepository, breedOrder);

        assertEquals(expectedAction, breedDinosaurAction);
    }
}
