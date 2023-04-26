package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.List;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;

public class ResourcesExpirationConsequence implements Action {
    private final int currentTurn;
    private final ResourceRepository resourceRepository;

    public ResourcesExpirationConsequence(ResourceRepository resourceRepository, int currentTurn) {
        this.currentTurn = currentTurn;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void execute() {
        List<Resource> resources = resourceRepository.readAll();
        resources.forEach(resource -> resource.expireIfPastDueDate(currentTurn));
        resourceRepository.addAll(resources);
    }
}
