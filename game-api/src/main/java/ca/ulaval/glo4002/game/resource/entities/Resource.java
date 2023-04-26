package ca.ulaval.glo4002.game.resource.entities;

import java.util.UUID;

import ca.ulaval.glo4002.game.resource.entities.enums.ResourceState;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;
import ca.ulaval.glo4002.game.resource.entities.exceptions.IllegalResourceConsumptionQuantityException;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceState.*;

public class Resource {
    private final UUID id;
    private int freshQuantity;
    private int consumedQuantity;
    private int expiredQuantity;
    private final int creationTurn;
    private final ResourceType type;

    public Resource(int quantity, int creationTurn, ResourceType type) {
        this.id = UUID.randomUUID();
        this.freshQuantity = quantity;
        this.creationTurn = creationTurn;
        this.type = type;
    }

    public Resource(Resource other) {
        id = other.id;
        freshQuantity = other.freshQuantity;
        consumedQuantity = other.consumedQuantity;
        expiredQuantity = other.expiredQuantity;
        creationTurn = other.creationTurn;
        type = other.type;
    }

    public UUID getId() {
        return id;
    }

    public int getCreationTurn() {
        return creationTurn;
    }

    public ResourceType getType() {
        return type;
    }

    public int getQuantity(ResourceState state) {
        if (state.equals(FRESH)) {
            return freshQuantity;
        } else if (state.equals(CONSUMED)) {
            return consumedQuantity;
        } else if (state.equals(EXPIRED)) {
            return expiredQuantity;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void consumeQuantity(int value) {
        if (value > freshQuantity) {
            throw new IllegalResourceConsumptionQuantityException();
        }
        freshQuantity -= value;
        consumedQuantity += value;
    }

    public int consumeMaximum(int maximum) {
        int quantityToConsume = Math.min(freshQuantity, maximum);
        consumeQuantity(quantityToConsume);
        return quantityToConsume;
    }

    public void expireIfPastDueDate(int currentTurn) {
        if (currentTurn >= (creationTurn + type.getShelfLife())) {
            expiredQuantity += freshQuantity;
            freshQuantity = 0;
        }
    }

    public boolean isOfType(ResourceType resourceType) {
        return type.equals(resourceType);
    }

    @Override
    public boolean equals(Object resource) {
        if (!(resource instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) resource;

        return freshQuantity == other.freshQuantity
                && consumedQuantity == other.consumedQuantity
                && expiredQuantity == other.expiredQuantity
                && creationTurn == other.creationTurn
                && type.equals(other.type);
    }
}
