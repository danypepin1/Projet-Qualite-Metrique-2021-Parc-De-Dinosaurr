package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.StrengthDifference;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfiguration;
import ca.ulaval.glo4002.game.resource.entities.FoodBank;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender.FEMALE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender.MALE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.ANKYLOSAURUS;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.VELOCIRAPTOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DinosaurTest {
    private static final String SOME_NAME = "Bob";
    private static final Integer SOME_WEIGHT = 1000;
    private static final DinosaurGender SOME_GENDER = FEMALE;
    private static final DinosaurSpecies SOME_SPECIES = ANKYLOSAURUS;
    private static final int STRONGER_STRENGTH = 4821;

    private static final String SOME_OTHER_NAME = "Alice";
    private static final Integer SOME_OTHER_WEIGHT = 2432;
    private static final DinosaurGender SOME_OTHER_GENDER = MALE;
    private static final DinosaurSpecies SOME_OTHER_SPECIES = VELOCIRAPTOR;
    private static final int WEAKER_STRENGTH = 1209;

    private FoodBank someFoodBank;

    private HungerStrategyFactory someHungerStrategyFactory;
    private HungerStrategy someHungerStrategy;
    private Hunger someHunger;

    private StrengthConfiguration someStrengthConfiguration;
    private Dinosaur someDinosaur;

    private StrengthConfiguration someOtherStrengthConfiguration;
    private Dinosaur someOtherDinosaur;

    private Dinosaur someMaleDinosaur;
    private Dinosaur someFemaleDinosaur;

    @BeforeEach
    public void setup() {
        setupFoodBank();
        setupHungerStrategy();
        setupDinosaur();
        setupOtherDinosaur();
        setupGenderedDinosaurs();
    }

    private void setupFoodBank() {
        someFoodBank = mock(FoodBank.class);
    }

    private void setupHungerStrategy() {
        someHungerStrategyFactory = mock(HungerStrategyFactory.class);
        someHungerStrategy = mock(HungerStrategy.class);
        someHunger = mock(Hunger.class);
        when(someHungerStrategyFactory.create(any(), anyInt(), anyBoolean())).thenReturn(someHungerStrategy);
        when(someHungerStrategy.calculate()).thenReturn(someHunger);
    }

    private void setupDinosaur() {
        someStrengthConfiguration = mock(StrengthConfiguration.class);
        someDinosaur = new Dinosaur(
                SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES, someHungerStrategyFactory, someStrengthConfiguration
        );
        someDinosaur.calculateHunger();
    }

    private void setupOtherDinosaur() {
        someOtherStrengthConfiguration = mock(StrengthConfiguration.class);
        someOtherDinosaur = new Dinosaur(
                SOME_OTHER_NAME, SOME_OTHER_WEIGHT, SOME_OTHER_GENDER, SOME_OTHER_SPECIES,
                someHungerStrategyFactory, someOtherStrengthConfiguration
        );
        someOtherDinosaur.calculateHunger();
    }

    private void setupGenderedDinosaurs() {
        someMaleDinosaur = new Dinosaur(
            SOME_NAME, SOME_WEIGHT, MALE, SOME_SPECIES, someHungerStrategyFactory, someStrengthConfiguration
        );
        someFemaleDinosaur = new Dinosaur(
            SOME_NAME, SOME_WEIGHT, FEMALE, SOME_SPECIES, someHungerStrategyFactory, someStrengthConfiguration
        );
    }

    @Test
    public void givenOtherDinosaur_whenComparing_thenReturnsStrengthMinusOthersStrength() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);

        int actual = someDinosaur.compareTo(someOtherDinosaur);

        int expected = STRONGER_STRENGTH - WEAKER_STRENGTH;
        assertEquals(expected, actual);
    }

    @Test
    public void givenStrongerDinosaur_whenComparingStrength_thenIsStronger() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);

        StrengthDifference actual = someDinosaur.compareStrength(someOtherDinosaur);

        assertEquals(StrengthDifference.STRONGER, actual);
    }

    @Test
    public void givenStrongerDinosaur_whenFight_thenHasNotLostChallenge() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertFalse(someDinosaur.hasLostChallenge());
    }

    @Test
    public void givenStrongerDinosaur_whenFight_thenIsStarving() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertTrue(someDinosaur.isStarving());
    }

    @Test
    public void givenStrongerDinosaur_whenFight_thenOtherHasLostChallenge() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertTrue(someOtherDinosaur.hasLostChallenge());
    }

    @Test
    public void givenStrongerDinosaur_whenFight_thenOtherIsNotStarving() {
        someOtherDinosaur.updateFedStatus();
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertFalse(someOtherDinosaur.isStarving());
    }

    @Test
    public void givenWeakerDinosaur_whenComparingStrength_thenIsWeaker() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        StrengthDifference actual = someDinosaur.compareStrength(someOtherDinosaur);

        assertEquals(StrengthDifference.WEAKER, actual);
    }

    @Test
    public void givenWeakerDinosaur_whenFight_thenHasLostChallenge() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertTrue(someDinosaur.hasLostChallenge());
    }

    @Test
    public void givenWeakerDinosaur_whenFight_thenIsNotStarving() {
        someDinosaur.updateFedStatus();
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertFalse(someDinosaur.isStarving());
    }

    @Test
    public void givenWeakerDinosaur_whenFight_thenOtherHasNotLostChallenge() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertFalse(someOtherDinosaur.hasLostChallenge());
    }

    @Test
    public void givenWeakerDinosaur_whenFight_thenOtherIsStarving() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(WEAKER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertTrue(someOtherDinosaur.isStarving());
    }

    @Test
    public void givenSameStrengthDinosaur_whenComparing_thenComparesNameWithOthersName() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        int actual = someDinosaur.compareTo(someOtherDinosaur);

        int expected = someOtherDinosaur.getName().compareTo(someDinosaur.getName());
        assertEquals(expected, actual);
    }

    @Test
    public void givenSameStrengthDinosaur_whenComparingStrength_thenIsEquals() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        StrengthDifference actual = someDinosaur.compareStrength(someOtherDinosaur);

        assertEquals(StrengthDifference.EQUAL, actual);
    }

    @Test
    public void givenSameStrengthDinosaur_whenFight_thenHasNotLostChallenge() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertFalse(someDinosaur.hasLostChallenge());
    }

    @Test
    public void givenSameStrengthDinosaur_whenFight_thenIsStarving() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertTrue(someDinosaur.isStarving());
    }

    @Test
    public void givenSameStrengthDinosaur_whenFight_thenOtherHasNotLostChallenge() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertFalse(someOtherDinosaur.hasLostChallenge());
    }

    @Test
    public void givenSameStrengthDinosaur_whenFight_thenOtherIsStarving() {
        when(someStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);
        when(someOtherStrengthConfiguration.calculate(anyInt())).thenReturn(STRONGER_STRENGTH);

        someDinosaur.fight(someOtherDinosaur);

        assertTrue(someOtherDinosaur.isStarving());
    }

    @Test
    public void givenDinosaur_whenCalculateHunger_thenCreatedHungerStrategyCalculatesHunger() {
        clearInvocations(someHungerStrategy);

        someDinosaur.calculateHunger();

        verify(someHungerStrategy).calculate();
    }

    @Test
    public void givenHungryDinosaur_whenConsume_thenResourcesAreConsumedByHunger() {
        someDinosaur.consume(someFoodBank);

        verify(someHunger).consume(someFoodBank);
    }

    @Test
    public void givenNotDepletedHunger_whenUpdateFedStatus_thenDinosaurIsFasted() {
        when(someHunger.isDepleted()).thenReturn(false);

        someDinosaur.updateFedStatus();

        assertTrue(someDinosaur.isFasted());
    }

    @Test
    public void givenDepletedHunger_whenUpdateFedStatus_thenDinosaurIsNotFasted() {
        when(someHunger.isDepleted()).thenReturn(true);

        someDinosaur.updateFedStatus();

        assertFalse(someDinosaur.isFasted());
    }

    @Test
    public void givenFedDinosaur_whenUpdateFedStatus_thenIsNotStarving() {
        someDinosaur.updateFedStatus();

        assertFalse(someDinosaur.isStarving());
    }

    @Test
    public void givenMaleDinosaur_whenIsMale_thenReturnTrue() {
        boolean isMale = someMaleDinosaur.isMale();

        assertTrue(isMale);
    }

    @Test
    public void givenMaleDinosaur_whenIsFemale_thenReturnFalse() {
        boolean isFemale = someMaleDinosaur.isFemale();

        assertFalse(isFemale);
    }

    @Test
    public void givenFemaleDinosaur_whenIsFemale_thenReturnTrue() {
        boolean isFemale = someFemaleDinosaur.isFemale();

        assertTrue(isFemale);
    }

    @Test
    public void givenFemaleDinosaur_whenIsMale_thenReturnFalse() {
        boolean isMale = someFemaleDinosaur.isMale();

        assertFalse(isMale);
    }
}
