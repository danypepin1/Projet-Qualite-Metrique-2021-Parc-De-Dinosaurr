package ca.ulaval.glo4002.game.resource.application.dtos;

import ca.ulaval.glo4002.game.resource.entities.ResourceOrder;

public class ResourceCreationDto implements ResourceOrder {
    public int qtyBurger;
    public int qtySalad;
    public int qtyWater;

    @Override
    public int getQtyBurger() {
        return qtyBurger;
    }

    @Override
    public int getQtySalad() {
        return qtySalad;
    }

    @Override
    public int getQtyWater() {
        return qtyWater;
    }

    @Override
    public boolean equals(Object order) {
        if (!(order instanceof ResourceCreationDto)) {
            return false;
        }
        ResourceCreationDto other = (ResourceCreationDto) order;
        return qtyBurger == other.qtyBurger
            && qtySalad == other.qtySalad
            && qtyWater == other.qtyWater;
    }
}
