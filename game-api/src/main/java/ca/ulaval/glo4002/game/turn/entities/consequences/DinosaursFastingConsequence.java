package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

public class DinosaursFastingConsequence implements Action {
    private final DinosaurRepository dinosaurRepository;

    public DinosaursFastingConsequence(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    @Override
    public void execute() {
        List<Dinosaur> dinosaurs = dinosaurRepository.readAll();
        dinosaurRepository.removeAll(
            dinosaurs.stream().filter(Dinosaur::isFasted).collect(Collectors.toList())
        );
    }
}
