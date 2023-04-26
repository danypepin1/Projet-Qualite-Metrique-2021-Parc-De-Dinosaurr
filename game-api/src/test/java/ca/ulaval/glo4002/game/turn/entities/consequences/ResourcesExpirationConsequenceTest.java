package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;

import static org.mockito.Mockito.*;

class ResourcesExpirationConsequenceTest {
    private static final int SOME_TURN_NUMBER = 42;

    private ResourceRepository resourceRepository;
    private ResourcesExpirationConsequence consequence;
    private Resource someResource;

    @BeforeEach
    public void setup() {
        resourceRepository = mock(ResourceRepository.class);
        setupResources();
        consequence = new ResourcesExpirationConsequence(resourceRepository, SOME_TURN_NUMBER);
    }

    private void setupResources() {
        someResource = mock(Resource.class);
        when(resourceRepository.readAll()).thenReturn(List.of(someResource));
    }

    @Test
    public void givenResource_whenExecute_thenResourceIsExpiredIfPastDueDate() {
        consequence.execute();

        verify(someResource).expireIfPastDueDate(SOME_TURN_NUMBER);
    }

    @Test
    public void givenResource_whenExecute_thenUpdatedResourceIsSaved() {
        consequence.execute();

        verify(resourceRepository, atLeastOnce()).addAll(List.of(someResource));
    }
}
