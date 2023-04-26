package ca.ulaval.glo4002.game.resource.application;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.resource.application.assemblers.ResourceAssembler;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;
import ca.ulaval.glo4002.game.resource.entities.ResourceCreationAction;
import ca.ulaval.glo4002.game.resource.entities.ResourceFactory;
import ca.ulaval.glo4002.game.resource.entities.ResourceOrder;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

public class ResourceUseCase {
    private final ResourceFactory resourceFactory;
    private final ResourceRepository resourceRepository;
    private final TurnRepository turnRepository;
    private final ActionRepository actionRepository;
    private final ResourceAssembler resourceAssembler;

    @Inject
    public ResourceUseCase(
        ResourceRepository resourceRepository, TurnRepository turnRepository, ActionRepository actionRepository,
        ResourceFactory resourceFactory, ResourceAssembler resourceAssembler
    ) {
        this.resourceRepository = resourceRepository;
        this.turnRepository = turnRepository;
        this.resourceFactory = resourceFactory;
        this.actionRepository = actionRepository;
        this.resourceAssembler = resourceAssembler;
    }

    public void createResourceAction(ResourceOrder resourceOrder) {
        Action resourceCreationAction = new ResourceCreationAction(
            resourceRepository, turnRepository, resourceFactory, resourceOrder
        );
        actionRepository.add(resourceCreationAction);
    }

    public ResourceInventoryDto getResourceInventory() {
        return resourceAssembler.toDto(
            resourceRepository.readAll()
        );
    }
}
