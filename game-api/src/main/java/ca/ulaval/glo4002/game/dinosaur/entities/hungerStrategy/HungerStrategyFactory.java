package ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet.CARNIVORE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet.HERBIVORE;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet.OMNIVORE;

public class HungerStrategyFactory {
    public HungerStrategy create(DinosaurSpecies species, int weight, boolean isStarving) {
        if (species.toDiet().equals(CARNIVORE)) {
            return new CarnivoreHungerStrategy(weight, isStarving);
        } else if (species.toDiet().equals(HERBIVORE)) {
            return new HerbivoreHungerStrategy(weight, isStarving);
        } else if (species.toDiet().equals(OMNIVORE)) {
            return new OmnivoreHungerStrategy(weight, isStarving);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
