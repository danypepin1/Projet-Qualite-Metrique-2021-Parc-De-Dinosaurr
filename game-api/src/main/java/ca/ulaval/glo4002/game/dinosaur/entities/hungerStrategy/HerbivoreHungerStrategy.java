package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import ca.ulaval.glo4002.game.dinosaur.entities.Hunger;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.SALAD;

public class HerbivoreHungerStrategy extends HungerStrategy {
    protected static final double HERBIVORE_HUNGER_FACTOR = 0.5;

    public HerbivoreHungerStrategy(int weight, boolean isStarving) {
        super(weight, isStarving, DinosaurDiet.HERBIVORE);
    }

    @Override
    protected void calculateHunger(Hunger hunger) {
        hunger.setHungerOfResourceType(
            SALAD,
            (int) Math.ceil(weight * HERBIVORE_HUNGER_FACTOR / HUNGER_QUOTIENT_FACTOR * starvingHungerFactor())
        );
    }
}
