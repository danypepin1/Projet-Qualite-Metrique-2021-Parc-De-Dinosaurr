package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Hunger;

import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy.STARVING_FACTOR;
import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy.WATER_THIRST_FACTOR;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.WATER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HungerStrategyTest {
    private static final int SOME_WEIGHT = 1000;
    private static final boolean IS_STARVING = true;
    private static final boolean IS_NOT_STARVING = false;

    private HungerStrategy hungerStrategy;

    @Test
    public void givenStarving_whenCalculate_thenCalculatesThirstCorrectly() {
        hungerStrategy = new HerbivoreHungerStrategy(SOME_WEIGHT, IS_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        int expectedQty = (int) Math.ceil(SOME_WEIGHT * WATER_THIRST_FACTOR * STARVING_FACTOR);
        assertEquals(expectedQty, hunger.getHungerOfResourceType(WATER));
    }

    @Test
    public void givenNotStarving_whenCalculate_thenCalculatesThirstCorrectly() {
        hungerStrategy = new HerbivoreHungerStrategy(SOME_WEIGHT, IS_NOT_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        int expectedQty = (int) Math.ceil(SOME_WEIGHT * WATER_THIRST_FACTOR);
        assertEquals(expectedQty, hunger.getHungerOfResourceType(WATER));
    }
}
