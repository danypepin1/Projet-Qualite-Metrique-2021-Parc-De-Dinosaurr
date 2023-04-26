package ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfiguration;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfigurationFactory;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender.FEMALE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender.MALE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.BRACHIOSAURUS;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.TYRANNOSAURUS_REX;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.STRUTHIOMIMUS;
import static org.junit.jupiter.api.Assertions.*;

class StrengthConfigurationFactoryTest {
    private static final DinosaurSpecies SOME_CARNIVORE_SPECIES = TYRANNOSAURUS_REX;
    private static final DinosaurSpecies SOME_HERBIVORE_SPECIES = BRACHIOSAURUS;
    private static final DinosaurSpecies SOME_OMNIVORE_SPECIES = STRUTHIOMIMUS;
    private static final DinosaurSpecies SOME_SPECIES = BRACHIOSAURUS;
    private static final DinosaurGender SOME_GENDER = FEMALE;

    private static final double CARNIVORE_STRENGTH_FACTOR = 1.5;
    private static final double HERBIVORE_STRENGTH_FACTOR = 1.0;
    private static final double FEMALE_STRENGTH_FACTOR = 1.5;
    private static final double MALE_STRENGTH_FACTOR = 1.0;

    private StrengthConfigurationFactory strengthConfigurationFactory;

    @BeforeEach
    public void setup() {
        strengthConfigurationFactory = new StrengthConfigurationFactory();
    }

    @Test
    public void givenCarnivoreSpecies_whenCreate_thenDietFactorIsCarnivore() {
        StrengthConfiguration strengthConfiguration = strengthConfigurationFactory
            .create(SOME_CARNIVORE_SPECIES, SOME_GENDER);

        assertEquals(CARNIVORE_STRENGTH_FACTOR, strengthConfiguration.getDietStrengthFactor());
    }

    @Test
    public void givenHerbivoreSpecies_whenCreate_thenDietFactorIsHerbivore() {
        StrengthConfiguration strengthConfiguration = strengthConfigurationFactory
            .create(SOME_HERBIVORE_SPECIES, SOME_GENDER);

        assertEquals(HERBIVORE_STRENGTH_FACTOR, strengthConfiguration.getDietStrengthFactor());
    }

    @Test
    public void givenOmnivoreSpecies_whenCreate_thenDietFactorIsCarnivore() {
        StrengthConfiguration strengthConfiguration = strengthConfigurationFactory
            .create(SOME_OMNIVORE_SPECIES, SOME_GENDER);

        assertEquals(CARNIVORE_STRENGTH_FACTOR, strengthConfiguration.getDietStrengthFactor());
    }

    @Test
    public void givenMale_whenCreate_thenGenderFactorIsMale() {
        StrengthConfiguration strengthConfiguration = strengthConfigurationFactory
            .create(SOME_SPECIES, MALE);

        assertEquals(MALE_STRENGTH_FACTOR, strengthConfiguration.getGenderStrengthFactor());
    }

    @Test
    public void givenFemale_whenCreate_thenGenderFactorIsFemale() {
        StrengthConfiguration strengthConfiguration = strengthConfigurationFactory
            .create(SOME_SPECIES, FEMALE);

        assertEquals(FEMALE_STRENGTH_FACTOR, strengthConfiguration.getGenderStrengthFactor());
    }
}
