package ca.ulaval.glo4002.game.resource.entities;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceFactoryTest {
    private static final int SOME_QTY_BURGER = 2;
    private static final int SOME_QTY_SALAD = 3;
    private static final int SOME_QTY_WATER = 4;
    private static final int SOME_TURN = 84;

    private ResourceOrder someResourceOrder;
    private ResourceFactory resourceFactory;

    @BeforeEach
    public void setUp() {
        someResourceOrder = mock(ResourceOrder.class);
        when(someResourceOrder.getQtyBurger()).thenReturn(SOME_QTY_BURGER);
        when(someResourceOrder.getQtySalad()).thenReturn(SOME_QTY_SALAD);
        when(someResourceOrder.getQtyWater()).thenReturn(SOME_QTY_WATER);
        resourceFactory = new ResourceFactory();
    }

    @Test
    public void givenResourceOrder_whenCreate_thenResourcesAreCreated() {
        List<Resource> expectedResources = someResources();

        List<Resource> actualResources = resourceFactory.create(someResourceOrder, SOME_TURN);

        assertEquals(expectedResources, actualResources);
    }

    @Test
    public void givenResource_whenCreateCopy_thenCorrectCopyIsCreated() {
        Resource someResource = new Resource(SOME_QTY_SALAD, SOME_TURN, SALAD);

        Resource copy = new Resource(someResource);

        assertEquals(someResource, copy);
    }

    private List<Resource> someResources() {
        List<Resource> resources = new ArrayList<>();

        resources.add(new Resource(SOME_QTY_BURGER, SOME_TURN, BURGER));
        resources.add(new Resource(SOME_QTY_SALAD, SOME_TURN, SALAD));
        resources.add(new Resource(SOME_QTY_WATER, SOME_TURN, WATER));

        return resources;
    }
}
