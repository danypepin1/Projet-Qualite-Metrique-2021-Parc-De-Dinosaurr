package ca.ulaval.glo4002.game.resource.entities;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

public class ResourceCreationAction implements Action {
    private final ResourceRepository resourceRepository;
    private final TurnRepository turnRepository;
    private final ResourceFactory resourceFactory;

    private final ResourceOrder resourceOrder;

    @Inject
    public ResourceCreationAction(ResourceRepository resourceRepository, TurnRepository turnRepository,
                                  ResourceFactory resourceFactory, ResourceOrder resourceOrder) {
        this.resourceRepository = resourceRepository;
        this.turnRepository = turnRepository;
        this.resourceOrder = resourceOrder;
        this.resourceFactory = resourceFactory;
    }

    @Override
    public void execute() {
        int currentTurn = turnRepository.readTurnNumber();
        List<Resource> resourcesToSave = resourceFactory.create(resourceOrder, currentTurn);
        resourceRepository.addAll(resourcesToSave);
    }

    @Override
    public boolean equals(Object action) {
        if (!(action instanceof ResourceCreationAction)) {
            return false;
        }
        ResourceCreationAction other = (ResourceCreationAction) action;
        return resourceOrder.equals(other.resourceOrder);
    }
}
