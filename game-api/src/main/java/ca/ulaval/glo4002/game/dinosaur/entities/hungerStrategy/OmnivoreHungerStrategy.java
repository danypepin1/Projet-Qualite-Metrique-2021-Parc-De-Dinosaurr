package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import ca.ulaval.glo4002.game.dinosaur.entities.Hunger;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;

import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.CarnivoreHungerStrategy.CARNIVORE_HUNGER_FACTOR;
import static ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HerbivoreHungerStrategy.HERBIVORE_HUNGER_FACTOR;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.SALAD;

public class OmnivoreHungerStrategy extends HungerStrategy {
    public OmnivoreHungerStrategy(int weight, boolean isStarving) {
        super(weight, isStarving, DinosaurDiet.OMNIVORE);
    }

    @Override
    protected void calculateHunger(Hunger hunger) {
        hunger.setHungerOfResourceType(BURGER, calculateResourcesRequired(CARNIVORE_HUNGER_FACTOR));
        hunger.setHungerOfResourceType(SALAD, calculateResourcesRequired(HERBIVORE_HUNGER_FACTOR));
    }

    private int calculateResourcesRequired(double dietHungerFactor) {
        return (int) Math.ceil(
            (weight * dietHungerFactor) / HUNGER_QUOTIENT_FACTOR * starvingHungerFactor() / 2
        );
    }
}
