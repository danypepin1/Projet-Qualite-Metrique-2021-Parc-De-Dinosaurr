package ca.ulaval.glo4002.game.dinosaur.entities;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;

public class WeightModificationAction implements Action {
    private final DinosaurRepository dinosaurRepository;
    private final WeightModificationOrder weightModificationOrder;

    @Inject
    public WeightModificationAction(
        DinosaurRepository dinosaurRepository, WeightModificationOrder weightModificationOrder
    ) {
        this.dinosaurRepository = dinosaurRepository;
        this.weightModificationOrder = weightModificationOrder;
    }

    @Override
    public void execute() {
        Dinosaur dinosaur = dinosaurRepository.findByName(weightModificationOrder.getName());
        dinosaur.grow(weightModificationOrder.getWeight());
        dinosaurRepository.add(dinosaur);
    }

    @Override
    public boolean equals(Object action) {
        if (!(action instanceof WeightModificationAction)) {
            return false;
        }
        WeightModificationAction other = (WeightModificationAction) action;
        return weightModificationOrder.equals(other.weightModificationOrder);
    }
}
