package ca.ulaval.glo4002.game.resource.api.assemblers;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.resource.api.ResourceRequestValidator;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.resource.api.exceptions.NegativeResourceQuantityException;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceCreationDto;

public class ResourceDtoAssembler {
    private final ResourceRequestValidator resourceRequestValidator;

    @Inject
    public ResourceDtoAssembler(ResourceRequestValidator resourceRequestValidator) {
        this.resourceRequestValidator = resourceRequestValidator;
    }

    public ResourceCreationDto fromRequest(ResourceRequest resourceRequest) throws NegativeResourceQuantityException {
        resourceRequestValidator.validate(resourceRequest);

        ResourceCreationDto dto = new ResourceCreationDto();
        dto.qtyBurger = resourceRequest.qtyBurger;
        dto.qtySalad = resourceRequest.qtySalad;
        dto.qtyWater = resourceRequest.qtyWater;
        return dto;
    }
}
