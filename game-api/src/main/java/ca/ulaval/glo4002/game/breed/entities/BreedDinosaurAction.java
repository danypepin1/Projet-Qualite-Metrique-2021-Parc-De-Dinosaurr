package ca.ulaval.glo4002.game.breed.entities;

import java.util.Optional;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

public class BreedDinosaurAction implements Action {
    private final DinosaurRepository dinosaurRepository;
    private final BreedOrder breedOrder;
    private final Veterinary veterinary;

    @Inject
    public BreedDinosaurAction(Veterinary veterinary, DinosaurRepository dinosaurRepository, BreedOrder breedOrder) {
        this.veterinary = veterinary;
        this.dinosaurRepository = dinosaurRepository;
        this.breedOrder = breedOrder;
    }

    @Override
    public void execute() {
        if (dinosaurRepository.contains(breedOrder.getBabyName())) {
            return;
        }
        Dinosaur fatherDinosaur = dinosaurRepository.findByName(breedOrder.getFatherName());
        Dinosaur motherDinosaur = dinosaurRepository.findByName(breedOrder.getMotherName());
        Optional<Dinosaur> dinosaurToSave = veterinary.breed(breedOrder.getBabyName(), fatherDinosaur, motherDinosaur);
        dinosaurToSave.ifPresent(dinosaurRepository::add);
    }

    @Override
    public boolean equals(Object action) {
        if (!(action instanceof BreedDinosaurAction)) {
            return false;
        }
        BreedDinosaurAction other = (BreedDinosaurAction) action;
        return breedOrder.equals(other.breedOrder);
    }
}
