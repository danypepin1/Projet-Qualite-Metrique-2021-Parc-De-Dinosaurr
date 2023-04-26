package ca.ulaval.glo4002.game.resource.api.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.api.dtos.ResourceInventoriesResponse;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceInventoryDtoAssemblerTest {
    private static final int SOME_FRESH_BURGER_QTY = 234;
    private static final int SOME_FRESH_SALAD_QTY = 324;
    private static final int SOME_FRESH_WATER_QTY = 111;

    private static final int SOME_CONSUMED_BURGER_QTY = 63;
    private static final int SOME_CONSUMED_SALAD_QTY = 41;
    private static final int SOME_CONSUMED_WATER_QTY = 277;

    private static final int SOME_EXPIRED_BURGER_QTY = 2;
    private static final int SOME_EXPIRED_SALAD_QTY = 103;
    private static final int SOME_EXPIRED_WATER_QTY = 100;

    private ResourceInventoryDto someResourceInventoryDto;
    private ResourceInventoryDtoAssembler resourceInventoryDtoAssembler;

    @BeforeEach
    public void setUp() {
        someResourceInventoryDto = someResourceInventoryDto();
        resourceInventoryDtoAssembler = new ResourceInventoryDtoAssembler();
    }

    @Test
    public void givenInventoryDto_whenToResponse_thenResponseIsCorrect() {
        ResourceInventoriesResponse response = resourceInventoryDtoAssembler.toResponse(someResourceInventoryDto);

        assertEquals(SOME_FRESH_BURGER_QTY, response.fresh.qtyBurger);
        assertEquals(SOME_FRESH_SALAD_QTY, response.fresh.qtySalad);
        assertEquals(SOME_FRESH_WATER_QTY, response.fresh.qtyWater);
        assertEquals(SOME_CONSUMED_BURGER_QTY, response.consumed.qtyBurger);
        assertEquals(SOME_CONSUMED_SALAD_QTY, response.consumed.qtySalad);
        assertEquals(SOME_CONSUMED_WATER_QTY, response.consumed.qtyWater);
        assertEquals(SOME_EXPIRED_BURGER_QTY, response.expired.qtyBurger);
        assertEquals(SOME_EXPIRED_SALAD_QTY, response.expired.qtySalad);
        assertEquals(SOME_EXPIRED_WATER_QTY, response.expired.qtyWater);
    }

    private ResourceInventoryDto someResourceInventoryDto() {
        ResourceInventoryDto resourceInventoryDto = new ResourceInventoryDto();

        resourceInventoryDto.qtyFreshBurgers = SOME_FRESH_BURGER_QTY;
        resourceInventoryDto.qtyFreshSalads = SOME_FRESH_SALAD_QTY;
        resourceInventoryDto.qtyFreshWaters = SOME_FRESH_WATER_QTY;

        resourceInventoryDto.qtyConsumedBurgers = SOME_CONSUMED_BURGER_QTY;
        resourceInventoryDto.qtyConsumedSalads = SOME_CONSUMED_SALAD_QTY;
        resourceInventoryDto.qtyConsumedWaters = SOME_CONSUMED_WATER_QTY;

        resourceInventoryDto.qtyExpiredBurgers = SOME_EXPIRED_BURGER_QTY;
        resourceInventoryDto.qtyExpiredSalads = SOME_EXPIRED_SALAD_QTY;
        resourceInventoryDto.qtyExpiredWaters = SOME_EXPIRED_WATER_QTY;

        return resourceInventoryDto;
    }
}
