package ca.ulaval.glo4002.game.dinosaur.entities.factories;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurCreationAction;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurValidator;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;

public class DinosaurCreationActionFactory {
    private final DinosaurValidator dinosaurValidator;

    @Inject
    public DinosaurCreationActionFactory(DinosaurValidator dinosaurValidator) {
        this.dinosaurValidator = dinosaurValidator;
    }

    public DinosaurCreationAction create(
        DinosaurRepository dinosaurRepository, DinosaurFactory dinosaurFactory,
        String name, int weight, DinosaurGender gender, DinosaurSpecies species
    ) {
        dinosaurValidator.validateCreation(name, weight);
        return new DinosaurCreationAction(dinosaurRepository, dinosaurFactory, name, weight, gender, species);
    }
}
