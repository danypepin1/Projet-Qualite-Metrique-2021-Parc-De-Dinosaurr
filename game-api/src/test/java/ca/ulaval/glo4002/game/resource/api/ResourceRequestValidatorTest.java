package ca.ulaval.glo4002.game.resource.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.resource.api.exceptions.NegativeResourceQuantityException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceRequestValidatorTest {
    private static final int SOME_NEGATIVE_QTY_BURGER = -2;
    private static final int SOME_NEGATIVE_QTY_SALAD = -3;
    private static final int SOME_NEGATIVE_QTY_WATER = -4;
    private static final int SOME_QTY_BURGER = 2;
    private static final int SOME_QTY_SALAD = 3;
    private static final int SOME_QTY_WATER = 4;

    private ResourceRequestValidator resourceRequestValidator;

    @BeforeEach
    public void setUp() {
        resourceRequestValidator = new ResourceRequestValidator();
    }

    @Test
    public void givenResourceRequestWithNegativeQuantityBurger_whenValidate_thenThrowException() {
        ResourceRequest resourceRequest = new ResourceRequest();
        resourceRequest.qtyBurger = SOME_NEGATIVE_QTY_BURGER;

        assertThrows(
                NegativeResourceQuantityException.class,
                () -> resourceRequestValidator.validate(resourceRequest)
        );
    }

    @Test
    public void givenResourceRequestWithNegativeQuantitySalad_whenValidate_thenThrowException() {
        ResourceRequest resourceRequest = new ResourceRequest();
        resourceRequest.qtySalad = SOME_NEGATIVE_QTY_SALAD;

        assertThrows(
                NegativeResourceQuantityException.class,
                () -> resourceRequestValidator.validate(resourceRequest)
        );
    }

    @Test
    public void givenResourceRequestWithNegativeQuantityWater_whenValidate_thenThrowException() {
        ResourceRequest resourceRequest = new ResourceRequest();
        resourceRequest.qtyWater = SOME_NEGATIVE_QTY_WATER;

        assertThrows(
                NegativeResourceQuantityException.class,
                () -> resourceRequestValidator.validate(resourceRequest)
        );
    }

    @Test
    public void givenResourceRequestWithValidQuantity_whenValidate_thenDoNotThrowException() {
        ResourceRequest resourceRequest = new ResourceRequest();
        resourceRequest.qtyBurger = SOME_QTY_BURGER;
        resourceRequest.qtySalad = SOME_QTY_SALAD;
        resourceRequest.qtyWater = SOME_QTY_WATER;

        assertDoesNotThrow(() -> resourceRequestValidator.validate(resourceRequest));
    }
}
