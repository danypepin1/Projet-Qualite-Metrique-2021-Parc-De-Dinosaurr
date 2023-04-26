package ca.ulaval.glo4002.game.dinosaur.application.dtos;

import ca.ulaval.glo4002.game.dinosaur.entities.WeightModificationOrder;

public class WeightModificationDto implements WeightModificationOrder {
    public String name;
    public int weight;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
