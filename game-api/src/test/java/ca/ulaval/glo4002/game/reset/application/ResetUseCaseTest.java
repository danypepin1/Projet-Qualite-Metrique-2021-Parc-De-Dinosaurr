package ca.ulaval.glo4002.game.reset.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResetUseCaseTest {
    private ResetUseCase resetUseCase;

    private DinosaurRepository dinosaurRepository;
    private ResourceRepository resourceRepository;
    private TurnRepository turnRepository;
    private ActionRepository actionRepository;
    private SumoChallengeRepository sumoChallengeRepository;

    @BeforeEach
    public void setup() {
        dinosaurRepository = mock(DinosaurRepository.class);
        resourceRepository = mock(ResourceRepository.class);
        turnRepository = mock(TurnRepository.class);
        actionRepository = mock(ActionRepository.class);
        sumoChallengeRepository = mock(SumoChallengeRepository.class);

        resetUseCase = new ResetUseCase(
            dinosaurRepository, resourceRepository, turnRepository, actionRepository, sumoChallengeRepository
        );
    }

    @Test
    public void givenActionRepository_whenReset_thenActionsAreCleared() {
        resetUseCase.reset();

        verify(actionRepository).clear();
    }

    @Test
    public void givenDinosaurRepository_whenReset_thenDinosaursAreCleared() {
        resetUseCase.reset();

        verify(dinosaurRepository).clear();
    }

    @Test
    public void givenResourceRepository_whenReset_thenResourcesAreCleared() {
        resetUseCase.reset();

        verify(resourceRepository).clear();
    }

    @Test
    public void givenSumoChallengeRepository_whenReset_thenSumoChallengesAreCleared() {
        resetUseCase.reset();

        verify(sumoChallengeRepository).clear();
    }

    @Test
    public void givenTurnRepository_whenReset_thenTurnNumberIsReset() {
        resetUseCase.reset();

        verify(turnRepository).resetTurnNumber();
    }
}
