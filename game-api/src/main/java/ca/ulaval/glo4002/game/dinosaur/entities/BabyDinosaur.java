package ca.ulaval.glo4002.game.dinosaur.entities;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfiguration;

public class BabyDinosaur  extends Dinosaur {
    private static final int INITIAL_BABY_WEIGHT = 1;

    private final String fatherName;
    private final String motherName;

    public BabyDinosaur(
        String name, DinosaurGender gender, DinosaurSpecies species, String fatherName, String motherName,
        HungerStrategyFactory hungerStrategyFactory, StrengthConfiguration strengthConfiguration
    ) {
        super(name, INITIAL_BABY_WEIGHT, gender, species, hungerStrategyFactory, strengthConfiguration);
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public BabyDinosaur(BabyDinosaur other) {
        super(other);
        this.fatherName = other.fatherName;
        this.motherName = other.motherName;
    }

    public boolean isChildOf(String name) {
        return fatherName.equals(name) || motherName.equals(name);
    }
}
