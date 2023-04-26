package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import ca.ulaval.glo4002.game.dinosaur.entities.Hunger;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.BURGER;

public class CarnivoreHungerStrategy extends HungerStrategy {
    protected static final double CARNIVORE_HUNGER_FACTOR = 0.2;

    public CarnivoreHungerStrategy(int weight, boolean isStarving) {
        super(weight, isStarving, DinosaurDiet.CARNIVORE);
    }

    @Override
    protected void calculateHunger(Hunger hunger) {
        hunger.setHungerOfResourceType(
            BURGER,
            (int) Math.ceil(weight * CARNIVORE_HUNGER_FACTOR / HUNGER_QUOTIENT_FACTOR * starvingHungerFactor())
        );
    }
}
