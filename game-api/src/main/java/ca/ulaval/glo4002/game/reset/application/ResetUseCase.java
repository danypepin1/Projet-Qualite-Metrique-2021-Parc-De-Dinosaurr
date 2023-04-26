package ca.ulaval.glo4002.game.reset.application;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

public class ResetUseCase {
    private final DinosaurRepository dinosaurRepository;
    private final ResourceRepository resourceRepository;
    private final TurnRepository turnRepository;
    private final ActionRepository actionRepository;
    private final SumoChallengeRepository sumoChallengeRepository;

    @Inject
    public ResetUseCase(
        DinosaurRepository dinosaurRepository, ResourceRepository resourceRepository, TurnRepository turnRepository,
        ActionRepository actionRepository, SumoChallengeRepository sumoChallengeRepository
    ) {
        this.dinosaurRepository = dinosaurRepository;
        this.resourceRepository = resourceRepository;
        this.turnRepository = turnRepository;
        this.actionRepository = actionRepository;
        this.sumoChallengeRepository = sumoChallengeRepository;
    }

    public void reset() {
        clearRepositories();
        resetTurn();
    }

    private void clearRepositories() {
        dinosaurRepository.clear();
        resourceRepository.clear();
        actionRepository.clear();
        sumoChallengeRepository.clear();
    }

    private void resetTurn() {
        turnRepository.resetTurnNumber();
    }
}
