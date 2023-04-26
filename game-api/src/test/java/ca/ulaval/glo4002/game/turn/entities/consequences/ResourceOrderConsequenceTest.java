package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;
import static org.mockito.Mockito.*;

class ResourceOrderConsequenceTest {
    private static final int SOME_TURN_NUMBER = 42;
    private static final int BURGER_ORDER_QUANTITY = 100;
    private static final int SALAD_ORDER_QUANTITY = 250;
    private static final int WATER_ORDER_QUANTITY = 10000;

    private ResourcesOrderConsequence consequence;
    private ResourceRepository resourceRepository;

    @BeforeEach
    public void setup() {
        resourceRepository = mock(ResourceRepository.class);
        consequence = new ResourcesOrderConsequence(resourceRepository, SOME_TURN_NUMBER);
    }

    @Test
    public void givenConsequence_whenExecute_thenResourceOrderIsSaved() {
        consequence.execute();

        List<Resource> expectedResourcesOrder = Arrays.asList(
                new Resource(BURGER_ORDER_QUANTITY, SOME_TURN_NUMBER, BURGER),
                new Resource(SALAD_ORDER_QUANTITY, SOME_TURN_NUMBER, SALAD),
                new Resource(WATER_ORDER_QUANTITY, SOME_TURN_NUMBER, WATER)
        );
        verify(resourceRepository).addAll(expectedResourcesOrder);
    }
}
