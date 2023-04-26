package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.CarnivoreHungerStrategy;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HerbivoreHungerStrategy;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategy;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.OmnivoreHungerStrategy;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.BRACHIOSAURUS;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.STRUTHIOMIMUS;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.TYRANNOSAURUS_REX;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HungerStrategyFactoryTest {
    private static final DinosaurSpecies SOME_CARNIVORE_SPECIES = TYRANNOSAURUS_REX;
    private static final DinosaurSpecies SOME_HERBIVORE_SPECIES = BRACHIOSAURUS;
    private static final DinosaurSpecies SOME_OMNIVORE_SPECIES = STRUTHIOMIMUS;
    private static final int SOME_WEIGHT = 1000;
    private static final boolean SOME_IS_STARVING = true;

    private HungerStrategyFactory hungerStrategyFactory;

    @BeforeEach
    public void setup() {
        hungerStrategyFactory = new HungerStrategyFactory();
    }

    @Test
    public void givenCarnivoreSpecies_whenCreate_thenStrategyIsCarnivore() {
        HungerStrategy hungerStrategy = hungerStrategyFactory.create(
            SOME_CARNIVORE_SPECIES, SOME_WEIGHT, SOME_IS_STARVING
        );

        assertTrue(hungerStrategy instanceof CarnivoreHungerStrategy);
    }

    @Test
    public void givenHerbivoreSpecies_whenCreate_thenStrategyIsHerbivore() {
        HungerStrategy hungerStrategy = hungerStrategyFactory.create(
            SOME_HERBIVORE_SPECIES, SOME_WEIGHT, SOME_IS_STARVING
        );

        assertTrue(hungerStrategy instanceof HerbivoreHungerStrategy);
    }

    @Test
    public void givenOmnivoreSpecies_whenCreate_thenStrategyIsOmnivore() {
        HungerStrategy hungerStrategy = hungerStrategyFactory.create(
            SOME_OMNIVORE_SPECIES, SOME_WEIGHT, SOME_IS_STARVING
        );

        assertTrue(hungerStrategy instanceof OmnivoreHungerStrategy);
    }
}
