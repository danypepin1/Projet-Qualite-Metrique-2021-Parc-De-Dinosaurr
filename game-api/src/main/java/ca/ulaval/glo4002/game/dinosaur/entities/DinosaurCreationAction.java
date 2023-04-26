package ca.ulaval.glo4002.game.dinosaur.entities;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;

public class DinosaurCreationAction implements Action {
    private final DinosaurRepository dinosaurRepository;
    private final DinosaurFactory dinosaurFactory;
    private final String name;
    private final int weight;
    private final DinosaurGender gender;
    private final DinosaurSpecies species;

    @Inject
    public DinosaurCreationAction(
            DinosaurRepository dinosaurRepository, DinosaurFactory dinosaurFactory,
            String name, int weight, DinosaurGender gender, DinosaurSpecies species
    ) {
        this.dinosaurRepository = dinosaurRepository;
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        this.dinosaurFactory = dinosaurFactory;
    }

    @Override
    public void execute() {
        if (dinosaurRepository.contains(name)) {
            return;
        }
        Dinosaur dinosaurToSave = dinosaurFactory.create(name, weight, gender, species);
        dinosaurRepository.add(dinosaurToSave);
    }

    @Override
    public boolean equals(Object action) {
        if (!(action instanceof DinosaurCreationAction)) {
            return false;
        }
        DinosaurCreationAction other = (DinosaurCreationAction) action;

        return name.equals(other.name)
                && weight == other.weight
                && gender.equals(other.gender)
                && species.equals(other.species);
    }
}
