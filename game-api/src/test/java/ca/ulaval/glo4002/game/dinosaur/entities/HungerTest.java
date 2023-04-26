package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;
import ca.ulaval.glo4002.game.resource.entities.FoodBank;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class HungerTest {
    private static final int SOME_BURGER_HUNGER = 12;
    private static final int SOME_SALAD_HUNGER = 4;
    private static final int SOME_WATER_THIRST = 532;

    private static final DinosaurDiet SOME_DIET = DinosaurDiet.CARNIVORE;
    private static final ResourceType SOME_RESOURCE_TYPE = BURGER;

    private Hunger hunger;
    private FoodBank foodBank;

    @BeforeEach
    public void setup() {
        foodBank = mock(FoodBank.class);
    }

    private void givenHunger(DinosaurDiet diet) {
        hunger = new Hunger(diet);
        hunger.setHungerOfResourceType(BURGER, SOME_BURGER_HUNGER);
        hunger.setHungerOfResourceType(SALAD, SOME_SALAD_HUNGER);
        hunger.setHungerOfResourceType(WATER, SOME_WATER_THIRST);
    }

    private void givenNoHunger() {
        hunger = new Hunger(HungerTest.SOME_DIET);
    }

    @Test
    public void givenNoHunger_whenVerifyingDepletion_thenIsDepleted() {
        givenNoHunger();

        boolean isDepleted = hunger.isDepleted();

        assertTrue(isDepleted);
    }

    @Test
    public void givenHunger_whenVerifyingDepletion_thenIsNotDepleted() {
        givenHunger(SOME_DIET);

        boolean isDepleted = hunger.isDepleted();

        assertFalse(isDepleted);
    }

    @Test
    public void givenHunger_whenConsume_thenWaterThirstIsConsumed() {
        givenHunger(SOME_DIET);
        when(foodBank.getFoodType()).thenReturn(SOME_RESOURCE_TYPE);

        hunger.consume(foodBank);

        verify(foodBank).consumeWater(SOME_WATER_THIRST);
    }

    @Test
    public void givenCarnivoreHunger_whenConsume_thenBurgerHungerIsConsumed() {
        givenHunger(DinosaurDiet.CARNIVORE);
        when(foodBank.getFoodType()).thenReturn(BURGER);

        hunger.consume(foodBank);

        verify(foodBank).consumeFood(SOME_BURGER_HUNGER);
    }

    @Test
    public void givenHerbivoreHunger_whenConsume_thenSaladHungerIsConsumed() {
        givenHunger(DinosaurDiet.HERBIVORE);
        when(foodBank.getFoodType()).thenReturn(SALAD);

        hunger.consume(foodBank);

        verify(foodBank).consumeFood(SOME_SALAD_HUNGER);
    }

    @Test
    public void givenOmnivoreHunger_whenConsume_thenHalfOfWaterThirstIsConsumed() {
        givenHunger(DinosaurDiet.OMNIVORE);
        when(foodBank.getFoodType()).thenReturn(BURGER);

        hunger.consume(foodBank);

        verify(foodBank).consumeWater(SOME_WATER_THIRST / 2);
    }
}
