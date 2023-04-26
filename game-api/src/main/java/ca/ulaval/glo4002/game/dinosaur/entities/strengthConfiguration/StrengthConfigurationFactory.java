package ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet.CARNIVORE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet.HERBIVORE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet.OMNIVORE;

public class StrengthConfigurationFactory {
    protected static final double CARNIVORE_STRENGTH_FACTOR = 1.5;
    protected static final double HERBIVORE_STRENGTH_FACTOR = 1.0;
    protected static final double FEMALE_STRENGTH_FACTOR = 1.5;
    protected static final double MALE_STRENGTH_FACTOR = 1.0;

    public StrengthConfiguration create(DinosaurSpecies species, DinosaurGender gender) {
        return new StrengthConfiguration(calculateDietStrengthFactor(species), calculateGenderStrengthFactor(gender));
    }

    private double calculateDietStrengthFactor(DinosaurSpecies species) {
        if (species.toDiet().equals(CARNIVORE) || species.toDiet().equals(OMNIVORE)) {
            return CARNIVORE_STRENGTH_FACTOR;
        } else if (species.toDiet().equals(HERBIVORE)) {
            return HERBIVORE_STRENGTH_FACTOR;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private double calculateGenderStrengthFactor(DinosaurGender gender) {
        if (gender.equals(DinosaurGender.FEMALE)) {
            return FEMALE_STRENGTH_FACTOR;
        } else if (gender.equals(DinosaurGender.MALE)) {
            return MALE_STRENGTH_FACTOR;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
