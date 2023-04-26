package ca.ulaval.glo4002.game.dinosaur.entities.factories;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurValidator;
import ca.ulaval.glo4002.game.dinosaur.entities.WeightModificationAction;
import ca.ulaval.glo4002.game.dinosaur.entities.WeightModificationOrder;

public class WeightModificationActionFactory {
    private final DinosaurValidator dinosaurValidator;
    private final DinosaurRepository dinosaurRepository;

    @Inject
    public WeightModificationActionFactory(DinosaurValidator dinosaurValidator, DinosaurRepository dinosaurRepository) {
        this.dinosaurValidator = dinosaurValidator;
        this.dinosaurRepository = dinosaurRepository;
    }

    public WeightModificationAction create(WeightModificationOrder weightModificationOrder) {
        dinosaurValidator.validateWeightUpdate(weightModificationOrder);
        return new WeightModificationAction(dinosaurRepository, weightModificationOrder);
    }
}
