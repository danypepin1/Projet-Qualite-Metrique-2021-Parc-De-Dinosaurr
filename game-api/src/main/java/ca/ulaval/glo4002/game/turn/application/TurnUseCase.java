package ca.ulaval.glo4002.game.turn.application;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;
import ca.ulaval.glo4002.game.turn.entities.consequences.ConsequenceExecutor;

public class TurnUseCase {
    private final TurnRepository turnRepository;
    private final ActionRepository actionRepository;
    private final ResourceRepository resourceRepository;
    private final DinosaurRepository dinosaurRepository;
    private final DinosaurFactory dinosaurFactory;
    private final ConsequenceExecutor consequenceExecutor;

    @Inject
    public TurnUseCase(
        TurnRepository turnRepository, ActionRepository actionRepository, ResourceRepository resourceRepository,
        DinosaurRepository dinosaurRepository, DinosaurFactory dinosaurFactory, ConsequenceExecutor consequenceExecutor
    ) {
        this.turnRepository = turnRepository;
        this.actionRepository = actionRepository;
        this.resourceRepository = resourceRepository;
        this.dinosaurRepository = dinosaurRepository;
        this.dinosaurFactory = dinosaurFactory;
        this.consequenceExecutor = consequenceExecutor;
    }

    public int executeTurn() {
        int currentTurn = this.turnRepository.readTurnNumber();

        executeActions();
        executeConsequences(currentTurn);

        turnRepository.incrementTurnNumber();

        return currentTurn;
    }

    private void executeActions() {
        actionRepository.readAll().forEach(Action::execute);
        actionRepository.clear();
    }

    private void executeConsequences(int currentTurn) {
        consequenceExecutor.executeResourcesOrder(resourceRepository, currentTurn);
        consequenceExecutor.executeResourcesExpiration(resourceRepository, currentTurn);
        consequenceExecutor.executeResourcesConsumption(resourceRepository, dinosaurRepository);
        consequenceExecutor.executeLosersRemoval(dinosaurRepository);
        consequenceExecutor.executeDinosaursFasting(dinosaurRepository);
        consequenceExecutor.executeOrphansRemoval(dinosaurRepository);
        consequenceExecutor.executeBabyAging(dinosaurRepository, dinosaurFactory);
    }
}
