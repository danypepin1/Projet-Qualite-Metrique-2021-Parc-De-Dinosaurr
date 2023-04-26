package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;

public class ResourcesOrderConsequence implements Action {
    private static final int NEW_BURGER_QUANTITY = 100;
    private static final int NEW_SALAD_QUANTITY = 250;
    private static final int NEW_WATER_LITERS_QUANTITY = 10000;

    private final int currentTurn;
    private final ResourceRepository resourceRepository;

    public ResourcesOrderConsequence(ResourceRepository resourceRepository, int currentTurn) {
        this.currentTurn = currentTurn;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void execute() {
        Resource newBurgerResource = new Resource(NEW_BURGER_QUANTITY, currentTurn, BURGER);
        Resource newSaladResource = new Resource(NEW_SALAD_QUANTITY, currentTurn, SALAD);
        Resource newWaterResource = new Resource(NEW_WATER_LITERS_QUANTITY, currentTurn, WATER);

        List<Resource> resources = Arrays.asList(newBurgerResource, newSaladResource, newWaterResource);
        resourceRepository.addAll(resources);
    }
}
