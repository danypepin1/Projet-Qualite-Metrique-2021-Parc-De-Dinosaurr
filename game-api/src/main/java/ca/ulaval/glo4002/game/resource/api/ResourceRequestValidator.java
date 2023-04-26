package ca.ulaval.glo4002.game.resource.api;

import ca.ulaval.glo4002.game.resource.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.resource.api.exceptions.NegativeResourceQuantityException;

public class ResourceRequestValidator {
    public void validate(ResourceRequest resourceRequest) throws NegativeResourceQuantityException {
        validatePositiveQuantities(resourceRequest);
    }

    private void validatePositiveQuantities(ResourceRequest resourceRequest) {
        if (resourceRequest.qtyBurger < 0 || resourceRequest.qtySalad < 0 || resourceRequest.qtyWater < 0) {
            throw new NegativeResourceQuantityException();
        }
    }
}
