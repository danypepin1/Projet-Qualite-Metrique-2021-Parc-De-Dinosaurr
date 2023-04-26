package ca.ulaval.glo4002.game.resource.application.assemblers;

import java.util.List;

import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;
import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceState;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceState.*;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.*;

public class ResourceAssembler {
    public ResourceInventoryDto toDto(List<Resource> resources) {
        ResourceInventoryDto resourceInventoryDto = new ResourceInventoryDto();

        resourceInventoryDto.qtyFreshBurgers = calculateQuantity(BURGER, FRESH, resources);
        resourceInventoryDto.qtyFreshSalads = calculateQuantity(SALAD, FRESH, resources);
        resourceInventoryDto.qtyFreshWaters = calculateQuantity(WATER, FRESH, resources);

        resourceInventoryDto.qtyConsumedBurgers = calculateQuantity(BURGER, CONSUMED, resources);
        resourceInventoryDto.qtyConsumedSalads = calculateQuantity(SALAD, CONSUMED, resources);
        resourceInventoryDto.qtyConsumedWaters = calculateQuantity(WATER, CONSUMED, resources);

        resourceInventoryDto.qtyExpiredBurgers = calculateQuantity(BURGER, EXPIRED, resources);
        resourceInventoryDto.qtyExpiredSalads = calculateQuantity(SALAD, EXPIRED, resources);
        resourceInventoryDto.qtyExpiredWaters = calculateQuantity(WATER, EXPIRED, resources);

        return resourceInventoryDto;
    }

    private int calculateQuantity(ResourceType type, ResourceState state, List<Resource> resources) {
        int quantity = 0;
        for (Resource resource : resources) {
            if (resource.isOfType(type)) {
                quantity += resource.getQuantity(state);
            }
        }
        return quantity;
    }
}
