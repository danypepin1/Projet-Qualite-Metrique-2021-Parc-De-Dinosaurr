package ca.ulaval.glo4002.game.breed.application;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.breed.entities.BreedActionFactory;
import ca.ulaval.glo4002.game.breed.entities.BreedOrder;
import ca.ulaval.glo4002.game.breed.entities.Veterinary;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

public class BreedUseCase {
    private final Veterinary veterinary;
    private final ActionRepository actionRepository;
    private final BreedActionFactory breedActionFactory;
    private final DinosaurRepository dinosaurRepository;

    @Inject
    public BreedUseCase(
        Veterinary veterinary, DinosaurRepository dinosaurRepository,
        ActionRepository actionRepository, BreedActionFactory breedActionFactory
    ) {
        this.actionRepository = actionRepository;
        this.breedActionFactory = breedActionFactory;
        this.dinosaurRepository = dinosaurRepository;
        this.veterinary = veterinary;
    }

    public void createBreedAction(BreedOrder breedOrder) {
        Action breedAction = breedActionFactory.create(veterinary, dinosaurRepository, breedOrder);
        actionRepository.add(breedAction);
    }
}
