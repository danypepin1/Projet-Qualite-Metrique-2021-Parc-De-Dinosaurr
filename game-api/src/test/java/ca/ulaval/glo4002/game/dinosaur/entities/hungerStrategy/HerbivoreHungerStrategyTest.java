package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Hunger;

import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HerbivoreHungerStrategy.HERBIVORE_HUNGER_FACTOR;
import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy.HUNGER_QUOTIENT_FACTOR;
import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy.STARVING_FACTOR;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;
import static org.junit.jupiter.api.Assertions.*;

class HerbivoreHungerStrategyTest {
    private static final int SOME_WEIGHT = 1000;
    private static final boolean IS_STARVING = true;
    private static final boolean IS_NOT_STARVING = false;

    private HungerStrategy hungerStrategy;

    @Test
    public void givenStarving_whenCalculate_thenCalculatesSaladHungerCorrectly() {
        hungerStrategy = new HerbivoreHungerStrategy(SOME_WEIGHT, IS_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        int expectedQty = (int) Math.ceil(
                (SOME_WEIGHT * HERBIVORE_HUNGER_FACTOR) / HUNGER_QUOTIENT_FACTOR * STARVING_FACTOR
        );
        assertEquals(expectedQty, hunger.getHungerOfResourceType(SALAD));
    }

    @Test
    public void givenNotStarving_whenCalculate_thenCalculatesSaladHungerCorrectly() {
        hungerStrategy = new HerbivoreHungerStrategy(SOME_WEIGHT, IS_NOT_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        int expectedQty = (int) Math.ceil(
                (SOME_WEIGHT * HERBIVORE_HUNGER_FACTOR) / HUNGER_QUOTIENT_FACTOR
        );
        assertEquals(expectedQty, hunger.getHungerOfResourceType(SALAD));
    }

    @Test
    public void givenStrategy_whenCalculate_thenSetsNoBurgerHunger() {
        hungerStrategy = new HerbivoreHungerStrategy(SOME_WEIGHT, IS_NOT_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        assertNull(hunger.getHungerOfResourceType(BURGER));
    }
}
