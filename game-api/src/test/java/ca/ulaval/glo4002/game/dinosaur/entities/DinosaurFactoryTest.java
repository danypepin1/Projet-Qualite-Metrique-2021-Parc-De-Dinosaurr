package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfigurationFactory;
import ca.ulaval.glo4002.game.resource.entities.FoodBank;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DinosaurFactoryTest {
    private static final String SOME_NAME = "Bob";
    private static final String SOME_FATHER_NAME = "Jack";
    private static final String SOME_MOTHER_NAME = "Mary";
    private static final int SOME_WEIGHT = 150;
    private static final DinosaurGender SOME_GENDER = DinosaurGender.MALE;
    private static final DinosaurSpecies SOME_SPECIES = DinosaurSpecies.ANKYLOSAURUS;
    private static final ResourceType SOME_FOOD_TYPE = ResourceType.SALAD;

    private DinosaurFactory dinosaurFactory;
    private FoodBank foodBank;

    @BeforeEach
    public void setup() {
        foodBank = mock(FoodBank.class);
        when(foodBank.getFoodType()).thenReturn(SOME_FOOD_TYPE);
        dinosaurFactory = new DinosaurFactory(new HungerStrategyFactory(), new StrengthConfigurationFactory());
    }

    private Dinosaur givenFastedDinosaur() {
        Dinosaur dinosaur = dinosaurFactory.create(SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES);
        dinosaur.calculateHunger();
        dinosaur.consume(foodBank);
        dinosaur.updateFedStatus();
        return dinosaur;
    }

    @Test
    public void givenDinosaurInfo_whenCreatingDinosaur_thenReturnsCorrectDinosaur() {
        Dinosaur dinosaur = dinosaurFactory.create(SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES);

        assertEquals(SOME_NAME, dinosaur.getName());
        assertEquals(SOME_WEIGHT, dinosaur.getWeight());
        assertEquals(SOME_GENDER, dinosaur.getGender());
        assertEquals(SOME_SPECIES, dinosaur.getSpecies());
    }

    @Test
    public void givenDinosaurInfo_whenCreatingDinosaur_thenReturnsStarvingDinosaur() {
        Dinosaur dinosaur = dinosaurFactory.create(SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES);

        assertTrue(dinosaur.isStarving());
    }

    @Test
    public void givenDinosaur_whenCreatingCopy_thenReturnsCorrectCopy() {
        Dinosaur dinosaur = dinosaurFactory.create(SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES);

        Dinosaur copy = dinosaurFactory.create(dinosaur);

        assertEquals(dinosaur.getName(), copy.getName());
        assertEquals(dinosaur.getWeight(), copy.getWeight());
        assertEquals(dinosaur.getGender(), copy.getGender());
        assertEquals(dinosaur.getSpecies(), copy.getSpecies());
    }

    @Test
    public void givenFastedDinosaur_whenCreatingCopy_thenReturnsFastedCopy() {
        Dinosaur dinosaur = givenFastedDinosaur();

        Dinosaur copy = dinosaurFactory.create(dinosaur);

        assertTrue(copy.isFasted());
    }

    @Test
    public void givenStarvingDinosaur_whenCreatingCopy_thenReturnsStarvingCopy() {
        Dinosaur dinosaur = dinosaurFactory.create(SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES);

        Dinosaur copy = dinosaurFactory.create(dinosaur);

        assertTrue(copy.isStarving());
    }

    @Test
    public void givenBaby_whenCreatingCopy_thenCopyIsBaby() {
        Dinosaur baby = dinosaurFactory.create(
            SOME_NAME, SOME_FATHER_NAME, SOME_MOTHER_NAME, SOME_GENDER, SOME_SPECIES
        );

        Dinosaur actual = dinosaurFactory.create(baby);

        assertTrue(actual instanceof BabyDinosaur);
    }

    @Test
    public void givenBaby_whenCreatingAdultCopy_thenCopyIsNotBaby() {
        BabyDinosaur baby = dinosaurFactory.create(
            SOME_NAME, SOME_FATHER_NAME, SOME_MOTHER_NAME, SOME_GENDER, SOME_SPECIES
        );

        Dinosaur actual = dinosaurFactory.createAdult(baby);

        assertFalse(actual instanceof BabyDinosaur);
    }
}
