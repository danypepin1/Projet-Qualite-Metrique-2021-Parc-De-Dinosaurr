package ca.ulaval.glo4002.game.breed.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.breed.entities.BreedActionFactory;
import ca.ulaval.glo4002.game.breed.entities.BreedDinosaurAction;
import ca.ulaval.glo4002.game.breed.entities.BreedOrder;
import ca.ulaval.glo4002.game.breed.entities.Veterinary;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

import static org.mockito.Mockito.*;

class BreedUseCaseTest {
    private Veterinary veterinary;
    private DinosaurRepository dinosaurRepository;
    private ActionRepository actionRepository;
    private BreedActionFactory breedActionFactory;
    private BreedUseCase breedUseCase;
    private BreedOrder breedOrder;
    private BreedDinosaurAction breedDinosaurAction;

    @BeforeEach
    public void setup() {
        veterinary = mock(Veterinary.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        actionRepository = mock(ActionRepository.class);
        breedActionFactory = mock(BreedActionFactory.class);
        breedDinosaurAction = mock(BreedDinosaurAction.class);
        breedOrder = mock(BreedOrder.class);
        breedUseCase = new BreedUseCase(veterinary, dinosaurRepository, actionRepository, breedActionFactory);
    }

    @Test
    public void givenBreedDto_whenCreateBreedAction_thenCreateFromBreedActionFactoryIsCalled() {
        breedUseCase.createBreedAction(breedOrder);

        verify(breedActionFactory).create(veterinary, dinosaurRepository, breedOrder);
    }

    @Test
    public void givenBreedDto_whenCreateBreedAction_thenSaveFromActionRepositoryIsCalled() {
        when(breedActionFactory.create(veterinary, dinosaurRepository, breedOrder)).thenReturn(breedDinosaurAction);

        breedUseCase.createBreedAction(breedOrder);

        verify(actionRepository).add(breedDinosaurAction);
    }
}
