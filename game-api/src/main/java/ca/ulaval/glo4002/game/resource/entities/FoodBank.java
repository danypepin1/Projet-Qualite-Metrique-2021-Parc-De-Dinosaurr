package ca.ulaval.glo4002.game.resource.entities;

import java.util.List;

import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceState.FRESH;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.WATER;

public class FoodBank {
    private final List<Resource> resources;
    private final ResourceType foodType;
    private int waterLitersQuantity;
    private int foodQuantity;

    public FoodBank(List<Resource> resources, ResourceType foodType) {
        this.resources = resources;
        this.foodType = foodType;
        calculateQuantities();
    }

    public ResourceType getFoodType() {
        return foodType;
    }

    public int consumeFood(int maximum) {
        int quantityToConsume = Math.min(foodQuantity, maximum);
        consumeResourceType(foodType, quantityToConsume);
        foodQuantity -= quantityToConsume;
        return quantityToConsume;
    }

    public int consumeWater(int maximum) {
        int quantityToConsume = Math.min(waterLitersQuantity, maximum);
        consumeResourceType(WATER, quantityToConsume);
        waterLitersQuantity -= quantityToConsume;
        return quantityToConsume;
    }

    private void consumeResourceType(ResourceType type, int quantity) {
        int quantityToConsume = quantity;
        for (Resource resource : resources) {
            if (quantityToConsume <= 0) {
                break;
            }
            if (resource.getType() != type) {
                continue;
            }
            quantityToConsume -= resource.consumeMaximum(quantityToConsume);
        }
    }

    private void calculateQuantities() {
        waterLitersQuantity = 0;
        foodQuantity = 0;
        for (Resource resource : resources) {
            if (resource.getType() == foodType) {
                foodQuantity += resource.getQuantity(FRESH);
            } else if (resource.getType() == ResourceType.WATER) {
                waterLitersQuantity += resource.getQuantity(FRESH);
            }
        }
        waterLitersQuantity = waterLitersQuantity / 2;
    }
}
