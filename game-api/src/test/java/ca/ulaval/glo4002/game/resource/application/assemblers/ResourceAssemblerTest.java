package ca.ulaval.glo4002.game.resource.application.assemblers;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;
import ca.ulaval.glo4002.game.resource.entities.Resource;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceState.*;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceAssemblerTest {
    private static final int SOME_FRESH_BURGER_QTY = 234;
    private static final int SOME_FRESH_SALAD_QTY = 324;
    private static final int SOME_FRESH_WATER_QTY = 111;

    private static final int SOME_CONSUMED_BURGER_QTY = 63;
    private static final int SOME_CONSUMED_SALAD_QTY = 41;
    private static final int SOME_CONSUMED_WATER_QTY = 277;

    private static final int SOME_EXPIRED_BURGER_QTY = 2;
    private static final int SOME_EXPIRED_SALAD_QTY = 103;
    private static final int SOME_EXPIRED_WATER_QTY = 100;

    private List<Resource> someResources;

    private ResourceAssembler resourceAssembler;

    @BeforeEach
    public void setUp() {
        Resource someBurgerResource = mock(Resource.class);
        when(someBurgerResource.isOfType(BURGER)).thenReturn(true);
        when(someBurgerResource.getQuantity(FRESH)).thenReturn(SOME_FRESH_BURGER_QTY);
        when(someBurgerResource.getQuantity(CONSUMED)).thenReturn(SOME_CONSUMED_BURGER_QTY);
        when(someBurgerResource.getQuantity(EXPIRED)).thenReturn(SOME_EXPIRED_BURGER_QTY);

        Resource someSaladResource = mock(Resource.class);
        when(someSaladResource.isOfType(SALAD)).thenReturn(true);
        when(someSaladResource.getQuantity(FRESH)).thenReturn(SOME_FRESH_SALAD_QTY);
        when(someSaladResource.getQuantity(CONSUMED)).thenReturn(SOME_CONSUMED_SALAD_QTY);
        when(someSaladResource.getQuantity(EXPIRED)).thenReturn(SOME_EXPIRED_SALAD_QTY);

        Resource someWaterResource = mock(Resource.class);
        when(someWaterResource.isOfType(WATER)).thenReturn(true);
        when(someWaterResource.getQuantity(FRESH)).thenReturn(SOME_FRESH_WATER_QTY);
        when(someWaterResource.getQuantity(CONSUMED)).thenReturn(SOME_CONSUMED_WATER_QTY);
        when(someWaterResource.getQuantity(EXPIRED)).thenReturn(SOME_EXPIRED_WATER_QTY);

        someResources = Arrays.asList(someBurgerResource, someSaladResource, someWaterResource);

        resourceAssembler = new ResourceAssembler();
    }

    @Test
    public void givenSomeResource_whenToDto_thenFreshQuantitiesAreConvertedToDto() {
        ResourceInventoryDto resourceInventoryDto = resourceAssembler.toDto(someResources);

        assertEquals(SOME_FRESH_BURGER_QTY, resourceInventoryDto.qtyFreshBurgers);
        assertEquals(SOME_FRESH_SALAD_QTY, resourceInventoryDto.qtyFreshSalads);
        assertEquals(SOME_FRESH_WATER_QTY, resourceInventoryDto.qtyFreshWaters);
    }

    @Test
    public void givenSomeResource_whenToDto_thenConsumedQuantitiesAreConvertedToDto() {
        ResourceInventoryDto resourceInventoryDto = resourceAssembler.toDto(someResources);

        assertEquals(SOME_CONSUMED_BURGER_QTY, resourceInventoryDto.qtyConsumedBurgers);
        assertEquals(SOME_CONSUMED_SALAD_QTY, resourceInventoryDto.qtyConsumedSalads);
        assertEquals(SOME_CONSUMED_WATER_QTY, resourceInventoryDto.qtyConsumedWaters);
    }

    @Test
    public void givenSomeResource_whenToDto_thenExpiredQuantitiesAreConvertedToDto() {
        ResourceInventoryDto resourceInventoryDto = resourceAssembler.toDto(someResources);

        assertEquals(SOME_EXPIRED_BURGER_QTY, resourceInventoryDto.qtyExpiredBurgers);
        assertEquals(SOME_EXPIRED_SALAD_QTY, resourceInventoryDto.qtyExpiredSalads);
        assertEquals(SOME_EXPIRED_WATER_QTY, resourceInventoryDto.qtyExpiredWaters);
    }
}
