package ca.ulaval.glo4002.game.resource.api.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceInventoryResponse {
    public final long qtyBurger;
    public final long qtySalad;
    public final long qtyWater;

    @JsonCreator
    public ResourceInventoryResponse(
            @JsonProperty(value = "qtyBurger", required = true) long burgerQuantity,
            @JsonProperty(value = "qtySalad", required = true) long saladQuantity,
            @JsonProperty(value = "qtyWater", required = true) long waterQuantity
    ) {
        this.qtyBurger = burgerQuantity;
        this.qtySalad = saladQuantity;
        this.qtyWater = waterQuantity;
    }
}
