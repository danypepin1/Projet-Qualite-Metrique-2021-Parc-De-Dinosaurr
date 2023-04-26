package ca.ulaval.glo4002.game.dinosaur.entities.factories;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.BabyDinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfigurationFactory;

public class DinosaurFactory {
    private final HungerStrategyFactory hungerStrategyFactory;
    private final StrengthConfigurationFactory strengthConfigurationFactory;

    @Inject
    public DinosaurFactory(
        HungerStrategyFactory hungerStrategyFactory, StrengthConfigurationFactory strengthConfigurationFactory
    ) {
        this.hungerStrategyFactory = hungerStrategyFactory;
        this.strengthConfigurationFactory = strengthConfigurationFactory;
    }

    public Dinosaur create(String name, int weight, DinosaurGender gender, DinosaurSpecies species) {
        return new Dinosaur(
            name, weight, gender, species, hungerStrategyFactory, strengthConfigurationFactory.create(species, gender)
        );
    }

    public Dinosaur create(Dinosaur other) {
        if (other instanceof BabyDinosaur) {
            return new BabyDinosaur((BabyDinosaur) other);
        }
        return new Dinosaur(other);
    }

    public Dinosaur createAdult(Dinosaur other) {
        return new Dinosaur(other);
    }

    public BabyDinosaur create(
        String name, String fatherName, String motherName, DinosaurGender gender, DinosaurSpecies species
    ) {
        return new BabyDinosaur(
            name, gender, species, fatherName, motherName,
            hungerStrategyFactory, strengthConfigurationFactory.create(species, gender)
        );
    }
}
