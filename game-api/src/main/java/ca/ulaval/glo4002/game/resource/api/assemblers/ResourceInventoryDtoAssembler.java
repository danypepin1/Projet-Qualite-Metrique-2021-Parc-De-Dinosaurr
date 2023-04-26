package ca.ulaval.glo4002.game.resource.api.assemblers;

import ca.ulaval.glo4002.game.resource.api.dtos.ResourceInventoriesResponse;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceInventoryResponse;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;

public class ResourceInventoryDtoAssembler {
    public ResourceInventoriesResponse toResponse(ResourceInventoryDto dto) {
        ResourceInventoryResponse freshInventory = new ResourceInventoryResponse(
                dto.qtyFreshBurgers,
                dto.qtyFreshSalads,
                dto.qtyFreshWaters
        );

        ResourceInventoryResponse consumedInventory = new ResourceInventoryResponse(
                dto.qtyConsumedBurgers,
                dto.qtyConsumedSalads,
                dto.qtyConsumedWaters
        );

        ResourceInventoryResponse expiredInventory = new ResourceInventoryResponse(
                dto.qtyExpiredBurgers,
                dto.qtyExpiredSalads,
                dto.qtyExpiredWaters
        );

        return new ResourceInventoriesResponse(freshInventory, expiredInventory, consumedInventory);
    }
}
