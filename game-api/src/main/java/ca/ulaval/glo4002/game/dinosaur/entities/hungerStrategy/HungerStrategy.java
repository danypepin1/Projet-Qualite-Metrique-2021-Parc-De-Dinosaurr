package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import ca.ulaval.glo4002.game.dinosaur.entities.Hunger;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.WATER;

public abstract class HungerStrategy {
    protected static final int HUNGER_QUOTIENT_FACTOR = 200;
    protected static final double WATER_THIRST_FACTOR = 0.6;
    protected static final int STARVING_FACTOR = 2;

    protected final int weight;
    protected final boolean isStarving;
    private final DinosaurDiet diet;

    public HungerStrategy(int weight, boolean isStarving, DinosaurDiet diet) {
        this.weight = weight;
        this.isStarving = isStarving;
        this.diet = diet;
    }

    public Hunger calculate() {
        Hunger hunger = new Hunger(diet);
        calculateHunger(hunger);
        calculateThirst(hunger);
        return hunger;
    }

    protected abstract void calculateHunger(Hunger hunger);

    protected void calculateThirst(Hunger hunger) {
        hunger.setHungerOfResourceType(
                WATER,
                (int) Math.ceil(weight * WATER_THIRST_FACTOR * starvingHungerFactor())
        );
    }

    protected int starvingHungerFactor() {
        return isStarving ? STARVING_FACTOR : 1;
    }
}
