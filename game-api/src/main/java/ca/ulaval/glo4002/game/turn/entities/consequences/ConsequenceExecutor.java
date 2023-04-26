package ca.ulaval.glo4002.game.turn.entities.consequences;

import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;

public class ConsequenceExecutor {
    public void executeResourcesOrder(ResourceRepository resourceRepository, int currentTurn) {
        new ResourcesOrderConsequence(resourceRepository, currentTurn).execute();
    }

    public void executeResourcesExpiration(ResourceRepository resourceRepository, int currentTurn) {
        new ResourcesExpirationConsequence(resourceRepository, currentTurn).execute();
    }

    public void executeResourcesConsumption(
        ResourceRepository resourceRepository, DinosaurRepository dinosaurRepository
    ) {
        new ResourcesConsumptionConsequence(resourceRepository, dinosaurRepository).execute();
    }

    public void executeLosersRemoval(DinosaurRepository dinosaurRepository) {
        new LosersRemovalConsequence(dinosaurRepository).execute();
    }

    public void executeDinosaursFasting(DinosaurRepository dinosaurRepository) {
        new DinosaursFastingConsequence(dinosaurRepository).execute();
    }

    public void executeOrphansRemoval(DinosaurRepository dinosaurRepository) {
        new OrphansRemovalConsequence(dinosaurRepository).execute();
    }

    public void executeBabyAging(DinosaurRepository dinosaurRepository, DinosaurFactory dinosaurFactory) {
        new BabiesAgingConsequence(dinosaurRepository, dinosaurFactory).execute();
    }
}
