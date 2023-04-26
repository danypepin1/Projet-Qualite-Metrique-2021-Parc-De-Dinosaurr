package ca.ulaval.glo4002.game.turn.entities.consequences;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.BabyDinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;

public class BabiesAgingConsequence implements Action {
    private static final int MAX_KILOGRAMS_WEIGHT = 100;
    private static final int GROW_KILOGRAMS_QUANTITY = 33;

    private final DinosaurRepository dinosaurRepository;
    private final DinosaurFactory dinosaurFactory;

    public BabiesAgingConsequence(DinosaurRepository dinosaurRepository, DinosaurFactory dinosaurFactory) {
        this.dinosaurRepository = dinosaurRepository;
        this.dinosaurFactory = dinosaurFactory;
    }

    @Override
    public void execute() {
        dinosaurRepository
            .readAll()
            .stream()
            .filter(dinosaur -> dinosaur instanceof BabyDinosaur)
            .forEach(baby -> {
                baby.grow(GROW_KILOGRAMS_QUANTITY);
                if (baby.getWeight() >= MAX_KILOGRAMS_WEIGHT) {
                    baby = dinosaurFactory.createAdult(baby);
                }
                dinosaurRepository.add(baby);
            });
    }
}
