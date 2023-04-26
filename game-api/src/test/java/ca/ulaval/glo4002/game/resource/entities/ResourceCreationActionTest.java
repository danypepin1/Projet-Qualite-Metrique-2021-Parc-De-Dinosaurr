package ca.ulaval.glo4002.game.resource.entities;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

import static org.mockito.Mockito.*;

class ResourceCreationActionTest {
    private static final int SOME_TURN = 57;

    private ResourceFactory resourceFactory;
    private ResourceRepository resourceRepository;
    private TurnRepository turnRepository;
    private ResourceCreationAction resourceCreationAction;
    private ResourceOrder resourceOrder;

    @BeforeEach
    public void setUp() {
        resourceFactory = mock(ResourceFactory.class);
        resourceRepository = mock(ResourceRepository.class);
        turnRepository = mock(TurnRepository.class);
        when(turnRepository.readTurnNumber()).thenReturn(SOME_TURN);
        resourceOrder = mock(ResourceOrder.class);

        resourceCreationAction = new ResourceCreationAction(
            resourceRepository, turnRepository, resourceFactory, resourceOrder
        );
    }

    @Test
    public void givenAction_whenExecute_thenTurnNumberIsRead() {
        resourceCreationAction.execute();

        verify(turnRepository, times(1)).readTurnNumber();
    }

    @Test
    public void givenAction_whenExecute_thenResourcesAreCreatedByTheFactory() {
        resourceCreationAction.execute();

        verify(resourceFactory).create(resourceOrder, SOME_TURN);
    }

    @Test
    public void givenAction_whenExecute_thenCreatedResourcesAreSaved() {
        List<Resource> expectedResources = Collections.singletonList(mock(Resource.class));
        when(resourceFactory.create(resourceOrder, SOME_TURN)).thenReturn(expectedResources);

        resourceCreationAction.execute();

        verify(resourceRepository).addAll(expectedResources);
    }
}
