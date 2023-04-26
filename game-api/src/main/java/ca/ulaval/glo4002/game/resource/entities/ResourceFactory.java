package ca.ulaval.glo4002.game.resource.entities;

import java.util.Arrays;
import java.util.List;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;

public class ResourceFactory {
    public List<Resource> create(ResourceOrder resourceOrder, int creationTurn) {
        return Arrays.asList(
            new Resource(resourceOrder.getQtyBurger(), creationTurn, BURGER),
            new Resource(resourceOrder.getQtySalad(), creationTurn, SALAD),
            new Resource(resourceOrder.getQtyWater(), creationTurn, WATER)
        );
    }

    public Resource create(Resource other) {
        return new Resource(other);
    }
}
