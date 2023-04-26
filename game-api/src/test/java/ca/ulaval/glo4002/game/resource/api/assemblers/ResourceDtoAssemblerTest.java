package ca.ulaval.glo4002.game.resource.api.assemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.api.ResourceRequestValidator;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceCreationDto;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class ResourceDtoAssemblerTest {
    private static final int SOME_QTY_BURGER = 2;
    private static final int SOME_QTY_SALAD = 3;
    private static final int SOME_QTY_WATER = 4;

    private ResourceDtoAssembler resourceDtoAssembler;
    private ResourceRequestValidator resourceRequestValidator;

    @BeforeEach
    public void setUp() {
        resourceRequestValidator = mock(ResourceRequestValidator.class);
        resourceDtoAssembler = new ResourceDtoAssembler(resourceRequestValidator);
    }

    @Test
    public void givenResourceRequest_whenFromRequest_thenReturnReturnCorrectResourceCreationDto() {
        ResourceRequest resourceRequest = someResourceRequest();
        doNothing().when(resourceRequestValidator).validate(resourceRequest);

        ResourceCreationDto actual = resourceDtoAssembler.fromRequest(resourceRequest);

        Assertions.assertEquals(SOME_QTY_BURGER, actual.qtyBurger);
        Assertions.assertEquals(SOME_QTY_SALAD, actual.qtySalad);
        Assertions.assertEquals(SOME_QTY_WATER, actual.qtyWater);
    }

    private ResourceRequest someResourceRequest() {
        ResourceRequest resourceRequest = new ResourceRequest();

        resourceRequest.qtyBurger = SOME_QTY_BURGER;
        resourceRequest.qtySalad = SOME_QTY_SALAD;
        resourceRequest.qtyWater = SOME_QTY_WATER;

        return resourceRequest;
    }
}
