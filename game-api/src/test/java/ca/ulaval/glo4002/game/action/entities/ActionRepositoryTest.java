package ca.ulaval.glo4002.game.action.entities;

import java.util.Queue;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.action.infrastructure.persistence.ActionRepositoryInMemory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ActionRepositoryTest {
    private static final Action FIRST_ACTION = mock(Action.class);
    private static final Action SECOND_ACTION = mock(Action.class);

    @Test
    public void givenEmptyRepository_whenReadingActions_thenQueueIsEmpty() {
        ActionRepository repository = new ActionRepositoryInMemory();

        Queue<Action> actions = repository.readAll();

        assertTrue(actions.isEmpty());
    }

    @Test
    public void givenEmptyRepository_whenSavingAction_thenActionIsSaved() {
        ActionRepository repository = new ActionRepositoryInMemory();

        repository.add(FIRST_ACTION);

        assertEquals(FIRST_ACTION, repository.readAll().peek());
    }

    @Test
    public void givenRepository_whenReadingActions_thenActionsAreInFifoOrder() {
        ActionRepository repository = new ActionRepositoryInMemory();
        repository.add(FIRST_ACTION);
        repository.add(SECOND_ACTION);

        Queue<Action> actions = repository.readAll();

        assertEquals(FIRST_ACTION, actions.remove());
        assertEquals(SECOND_ACTION, actions.peek());
    }

    @Test
    public void givenRepository_whenClearing_thenRepositoryIsEmpty() {
        ActionRepository repository = new ActionRepositoryInMemory();
        repository.add(FIRST_ACTION);
        repository.add(SECOND_ACTION);

        repository.clear();

        assertTrue(repository.readAll().isEmpty());
    }
}
