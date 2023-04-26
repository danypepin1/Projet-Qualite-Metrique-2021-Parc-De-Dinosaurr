package ca.ulaval.glo4002.game.dinosaur.entities;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidDinosaurWeightException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidWeightChangeException;

public class DinosaurValidator {
    private static final int MIN_ADULT_DINOSAUR_WEIGHT = 100;

    private final DinosaurRepository dinosaurRepository;

    @Inject
    public DinosaurValidator(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    public void validateCreation(String dinosaurName, int weight) {
        if (dinosaurRepository.contains(dinosaurName)) {
            throw new DuplicateNameException();
        }
        if (weight < MIN_ADULT_DINOSAUR_WEIGHT) {
            throw new InvalidDinosaurWeightException();
        }
    }

    public void validateWeightUpdate(WeightModificationOrder weightModificationOrder) {
        Dinosaur dinosaur = dinosaurRepository.findByName(weightModificationOrder.getName());
        if (dinosaur instanceof BabyDinosaur)  {
            throw new InvalidBabyWeightChangeException();
        }
        if (dinosaur.getWeight() + weightModificationOrder.getWeight() < MIN_ADULT_DINOSAUR_WEIGHT) {
            throw new InvalidWeightChangeException();
        }
    }
}
