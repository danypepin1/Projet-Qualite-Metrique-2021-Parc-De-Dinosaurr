package ca.ulaval.glo4002.game.turn.application;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;
import ca.ulaval.glo4002.game.turn.entities.consequences.ConsequenceExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TurnUseCaseTest {
    private static final int SOME_TURN_NUMBER = 42;

    private TurnUseCase turnUseCase;
    private TurnRepository turnRepository;
    private ActionRepository actionRepository;
    private ResourceRepository resourceRepository;
    private DinosaurRepository dinosaurRepository;
    private DinosaurFactory dinosaurFactory;
    private ConsequenceExecutor consequenceExecutor;
    private Action someAction;

    @BeforeEach
    public void setup() {
        setupRepositories();
        setupActions();
        dinosaurFactory = mock(DinosaurFactory.class);
        consequenceExecutor = mock(ConsequenceExecutor.class);
        turnUseCase = new TurnUseCase(
            turnRepository, actionRepository, resourceRepository, dinosaurRepository,
            dinosaurFactory, consequenceExecutor
        );
    }

    public void setupRepositories() {
        turnRepository = mock(TurnRepository.class);
        resourceRepository = mock(ResourceRepository.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        when(turnRepository.readTurnNumber()).thenReturn(SOME_TURN_NUMBER);
    }

    public void setupActions() {
        actionRepository = mock(ActionRepository.class);
        someAction = mock(Action.class);
        Queue<Action> someActions = new LinkedList<>();
        someActions.add(someAction);
        when(actionRepository.readAll()).thenReturn(someActions);
    }

    @Test
    public void givenUseCase_whenExecutingTurn_thenTurnNumberIsRead() {
        turnUseCase.executeTurn();

        verify(turnRepository).readTurnNumber();
    }

    @Test
    public void givenUseCase_whenExecutingTurn_thenActionsAreRead() {
        turnUseCase.executeTurn();

        verify(actionRepository).readAll();
    }

    @Test
    public void givenUseCase_whenExecutingTurn_thenActionsAreExecuted() {
        turnUseCase.executeTurn();

        verify(someAction).execute();
    }

    @Test
    public void givenUseCase_whenExecutingTurn_thenActionsAreCleared() {
        turnUseCase.executeTurn();

        verify(actionRepository).clear();
    }

    @Test
    public void givenUseCase_whenExecutingTurn_thenConsequencesAreExecutedInOrder() {
        InOrder inOrder = inOrder(consequenceExecutor);

        turnUseCase.executeTurn();

        inOrder.verify(consequenceExecutor).executeResourcesOrder(resourceRepository, SOME_TURN_NUMBER);
        inOrder.verify(consequenceExecutor).executeResourcesExpiration(resourceRepository, SOME_TURN_NUMBER);
        inOrder.verify(consequenceExecutor).executeResourcesConsumption(resourceRepository, dinosaurRepository);
        inOrder.verify(consequenceExecutor).executeLosersRemoval(dinosaurRepository);
        inOrder.verify(consequenceExecutor).executeDinosaursFasting(dinosaurRepository);
        inOrder.verify(consequenceExecutor).executeOrphansRemoval(dinosaurRepository);
        inOrder.verify(consequenceExecutor).executeBabyAging(dinosaurRepository, dinosaurFactory);
    }

    @Test
    public void givenUseCase_whenExecutingTurn_thenTurnNumberIsIncremented() {
        turnUseCase.executeTurn();

        verify(turnRepository).incrementTurnNumber();
    }

    @Test
    public void givenUseCase_whenExecutingTurn_thenTurnNumberIsReturned() {
        when(turnRepository.readTurnNumber()).thenReturn(SOME_TURN_NUMBER);

        int turnNumber = turnUseCase.executeTurn();

        assertEquals(SOME_TURN_NUMBER, turnNumber);
    }
}
