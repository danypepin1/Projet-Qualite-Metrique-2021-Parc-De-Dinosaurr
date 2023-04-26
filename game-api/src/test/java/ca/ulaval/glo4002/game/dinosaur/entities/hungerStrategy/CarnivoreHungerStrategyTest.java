package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Hunger;

import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.CarnivoreHungerStrategy.CARNIVORE_HUNGER_FACTOR;
import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy.HUNGER_QUOTIENT_FACTOR;
import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy.STARVING_FACTOR;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.SALAD;
import static org.junit.jupiter.api.Assertions.*;

class CarnivoreHungerStrategyTest {
    private static final int SOME_WEIGHT = 1000;
    private static final boolean IS_STARVING = true;
    private static final boolean IS_NOT_STARVING = false;

    private HungerStrategy hungerStrategy;

    @Test
    public void givenStarving_whenCalculate_thenCalculatesBurgerHungerCorrectly() {
        hungerStrategy = new CarnivoreHungerStrategy(SOME_WEIGHT, IS_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        int expectedQty = (int) Math.ceil(
                (SOME_WEIGHT * CARNIVORE_HUNGER_FACTOR) / HUNGER_QUOTIENT_FACTOR * STARVING_FACTOR
        );
        assertEquals(expectedQty, hunger.getHungerOfResourceType(BURGER));
    }

    @Test
    public void givenNotStarving_whenCalculate_thenCalculatesBurgerHungerCorrectly() {
        hungerStrategy = new CarnivoreHungerStrategy(SOME_WEIGHT, IS_NOT_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        int expectedQty = (int) Math.ceil(
                (SOME_WEIGHT * CARNIVORE_HUNGER_FACTOR) / HUNGER_QUOTIENT_FACTOR
        );
        assertEquals(expectedQty, hunger.getHungerOfResourceType(BURGER));
    }

    @Test
    public void givenStrategy_whenCalculate_thenSetsNoSaladHunger() {
        hungerStrategy = new CarnivoreHungerStrategy(SOME_WEIGHT, IS_STARVING);

        Hunger hunger = hungerStrategy.calculate();

        assertNull(hunger.getHungerOfResourceType(SALAD));
    }
}
