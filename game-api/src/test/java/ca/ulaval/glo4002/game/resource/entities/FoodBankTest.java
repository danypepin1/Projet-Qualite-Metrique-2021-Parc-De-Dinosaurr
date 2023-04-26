package ca.ulaval.glo4002.game.resource.entities;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.entities.enums.ResourceState;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FoodBankTest {
    private static final int SOME_BURGER_QTY = 32;
    private static final int SOME_SALAD_QTY = 29;
    private static final int SOME_WATER_QTY = 281;

    private static final int SOME_QUANTITY_TO_CONSUME = 12;
    private static final int SOME_TOO_LARGE_QUANTITY = 871;

    private Resource someBurgerResource;
    private Resource someSaladResource;
    private Resource someWaterResource;
    private List<Resource> someResources;

    private FoodBank carnivoreFoodBank;
    private FoodBank herbivoreFoodBank;
    private FoodBank someFoodBank;

    @BeforeEach
    public void setup() {
        setupResources();
        carnivoreFoodBank = new FoodBank(someResources, ResourceType.BURGER);
        herbivoreFoodBank = new FoodBank(someResources, ResourceType.SALAD);
        someFoodBank = carnivoreFoodBank;
    }

    private void setupResources() {
        someBurgerResource = someResource(SOME_BURGER_QTY, ResourceType.BURGER);
        someSaladResource = someResource(SOME_SALAD_QTY, ResourceType.SALAD);
        someWaterResource = someResource(SOME_WATER_QTY, ResourceType.WATER);

        someResources = Arrays.asList(someBurgerResource, someSaladResource, someWaterResource);
    }

    private Resource someResource(int quantity, ResourceType type) {
        Resource resource = mock(Resource.class);
        when(resource.getType()).thenReturn(type);
        when(resource.getQuantity(ResourceState.FRESH)).thenReturn(quantity);
        return resource;
    }

    @Test
    public void givenCarnivoreFoodBank_whenConsumeFood_thenBurgerResourceIsConsumed() {
        carnivoreFoodBank.consumeFood(SOME_QUANTITY_TO_CONSUME);

        verify(someBurgerResource).consumeMaximum(SOME_QUANTITY_TO_CONSUME);
    }

    @Test
    public void givenCarnivoreFoodBank_whenConsumeFood_thenSaladResourceIsNotConsumed() {
        carnivoreFoodBank.consumeFood(SOME_QUANTITY_TO_CONSUME);

        verify(someSaladResource, never()).consumeMaximum(SOME_QUANTITY_TO_CONSUME);
    }

    @Test
    public void givenHerbivoreFoodBank_whenConsumeFood_thenSaladResourceIsConsumed() {
        herbivoreFoodBank.consumeFood(SOME_QUANTITY_TO_CONSUME);

        verify(someSaladResource).consumeMaximum(SOME_QUANTITY_TO_CONSUME);
    }

    @Test
    public void givenHerbivoreFoodBank_whenConsumeFood_thenBurgerResourceIsNotConsumed() {
        herbivoreFoodBank.consumeFood(SOME_QUANTITY_TO_CONSUME);

        verify(someBurgerResource, never()).consumeMaximum(SOME_QUANTITY_TO_CONSUME);
    }

    @Test
    public void givenFoodBank_whenConsumeFood_thenConsumedQuantityIsReturned() {
        int actual = someFoodBank.consumeFood(SOME_QUANTITY_TO_CONSUME);

        assertEquals(SOME_QUANTITY_TO_CONSUME, actual);
    }

    @Test
    public void givenFoodBank_whenConsumeWater_thenUpToHalfOfWaterQuantityIsConsumed() {
        someFoodBank.consumeWater(SOME_WATER_QTY);

        int expected = SOME_WATER_QTY / 2;
        verify(someWaterResource).consumeMaximum(expected);
    }

    @Test
    public void givenInsufficientFoodBank_whenConsumeFood_thenConsumedQuantityIsReturned() {
        int actual = someFoodBank.consumeFood(SOME_TOO_LARGE_QUANTITY);

        assertEquals(SOME_BURGER_QTY, actual);
    }
}
