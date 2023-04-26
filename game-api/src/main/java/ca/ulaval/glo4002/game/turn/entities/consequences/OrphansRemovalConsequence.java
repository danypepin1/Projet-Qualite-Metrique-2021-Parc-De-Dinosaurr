package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.BabyDinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

public class OrphansRemovalConsequence implements Action {
    private final DinosaurRepository dinosaurRepository;

    public OrphansRemovalConsequence(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    @Override
    public void execute() {
        List<Dinosaur> dinosaurs = dinosaurRepository.readAll();

        List<Dinosaur> orphans = dinosaurs
                .stream()
                .filter(dinosaur -> dinosaur instanceof BabyDinosaur)
                .map(dinosaur -> (BabyDinosaur) dinosaur)
                .filter(baby -> dinosaurs.stream().noneMatch(
                        dinosaur -> baby.isChildOf(dinosaur.getName()))
                )
                .map(baby -> (Dinosaur) baby)
                .collect(Collectors.toList());

        dinosaurRepository.removeAll(orphans);
    }
}
