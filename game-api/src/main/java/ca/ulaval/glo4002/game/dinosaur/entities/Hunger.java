package ca.ulaval.glo4002.game.dinosaur.entities;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;
import ca.ulaval.glo4002.game.resource.entities.FoodBank;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;

public class Hunger {
    private final Map<ResourceType, Integer> hungerOfResourceType = new HashMap<>();
    private final DinosaurDiet diet;
    private int waterLitersQuantityPerDiet;

    public Hunger(DinosaurDiet diet) {
        this.diet = diet;
    }

    public void setHungerOfResourceType(ResourceType resourceType, int value) {
        if (resourceType == ResourceType.WATER) {
            if (diet == DinosaurDiet.OMNIVORE) {
                waterLitersQuantityPerDiet = (int) Math.ceil(value / 2.0);
            } else {
                waterLitersQuantityPerDiet = value;
            }
        }
        hungerOfResourceType.put(resourceType, value);
    }

    public Integer getHungerOfResourceType(ResourceType resourceType) {
        return hungerOfResourceType.get(resourceType);
    }

    public boolean isDepleted() {
        return hungerOfResourceType.values().stream().allMatch(v -> v <= 0);
    }

    public void consume(FoodBank foodBank) {
        consumeFood(foodBank);
        consumeWater(foodBank);
    }

    private void consumeFood(FoodBank foodBank) {
        ResourceType foodType = foodBank.getFoodType();
        int consumedQuantity = foodBank.consumeFood(hungerOfResourceType.get(foodType));
        updateHunger(foodType, consumedQuantity);
    }

    private void consumeWater(FoodBank foodBank) {
        int consumedQuantity = foodBank.consumeWater(waterLitersQuantityPerDiet);
        updateHunger(ResourceType.WATER, consumedQuantity);
    }

    private void updateHunger(ResourceType resourceType, int consumedQuantity) {
        hungerOfResourceType.put(
            resourceType,
            hungerOfResourceType.get(resourceType) - consumedQuantity
        );
    }
}
