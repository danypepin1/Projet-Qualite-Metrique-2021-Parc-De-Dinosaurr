package ca.ulaval.glo4002.game.breed.entities;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

public class BreedActionFactory {
    private final BreedValidator breedValidator;

    @Inject
    public BreedActionFactory(BreedValidator breedValidator) {
        this.breedValidator = breedValidator;
    }

    public BreedDinosaurAction create(Veterinary veterinary, DinosaurRepository repository, BreedOrder breedOrder) {
        breedValidator.validateBreed(breedOrder);
        return new BreedDinosaurAction(veterinary, repository, breedOrder);
    }
}
