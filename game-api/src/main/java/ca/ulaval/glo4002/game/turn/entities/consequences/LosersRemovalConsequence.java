package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

public class LosersRemovalConsequence implements Action {
    private final DinosaurRepository dinosaurRepository;

    public LosersRemovalConsequence(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    @Override
    public void execute() {
        List<Dinosaur> dinosaurs = dinosaurRepository.readAll();

        List<Dinosaur> losers = dinosaurs
                .stream()
                .filter(Dinosaur::hasLostChallenge)
                .collect(Collectors.toList());

        dinosaurRepository.removeAll(losers);
    }
}
