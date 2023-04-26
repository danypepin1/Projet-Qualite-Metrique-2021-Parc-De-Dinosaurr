package ca.ulaval.glo4002.game.resource.api.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceInventoriesResponse {
    public final ResourceInventoryResponse fresh;
    public final ResourceInventoryResponse expired;
    public final ResourceInventoryResponse consumed;

    @JsonCreator
    public ResourceInventoriesResponse(
            @JsonProperty(value = "fresh", required = true) ResourceInventoryResponse fresh,
            @JsonProperty(value = "expired", required = true) ResourceInventoryResponse expired,
            @JsonProperty(value = "consumed", required = true) ResourceInventoryResponse consumed
    ) {
        this.fresh = fresh;
        this.expired = expired;
        this.consumed = consumed;
    }
}
