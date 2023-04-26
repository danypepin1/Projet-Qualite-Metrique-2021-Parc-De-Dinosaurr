package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;
import ca.ulaval.glo4002.game.resource.entities.FoodBank;
import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;

public class ResourcesConsumptionConsequence implements Action {
    private final ResourceRepository resourceRepository;
    private final DinosaurRepository dinosaurRepository;

    public ResourcesConsumptionConsequence(
            ResourceRepository resourceRepository, DinosaurRepository dinosaurRepository
    ) {
        this.resourceRepository = resourceRepository;
        this.dinosaurRepository = dinosaurRepository;
    }

    @Override
    public void execute() {
        List<Resource> resources = resourceRepository.readAll();
        resources.sort(Comparator.comparingInt(Resource::getCreationTurn));

        FoodBank carnivoreFoodBank = new FoodBank(resources, ResourceType.BURGER);
        FoodBank herbivoreFoodBank = new FoodBank(resources, ResourceType.SALAD);

        List<Dinosaur> dinosaurs = dinosaurRepository.readAll();
        dinosaurs.forEach(Dinosaur::calculateHunger);

        executeByDiet(DinosaurDiet.CARNIVORE, dinosaurs, carnivoreFoodBank, Collections.reverseOrder());
        executeByDiet(DinosaurDiet.HERBIVORE, dinosaurs, herbivoreFoodBank, Dinosaur::compareTo);

        dinosaurs.forEach(Dinosaur::updateFedStatus);

        dinosaurRepository.addAll(dinosaurs);
        resourceRepository.addAll(resources);
    }

    private void executeByDiet(
        DinosaurDiet diet, List<Dinosaur> dinosaurs, FoodBank foodBank, Comparator<Dinosaur> comparator
    ) {
        dinosaurs
            .stream()
            .filter(dinosaur -> dinosaur.getDiet() == diet || dinosaur.getDiet() == DinosaurDiet.OMNIVORE)
            .sorted(comparator)
            .forEach(dinosaur -> dinosaur.consume(foodBank));
    }
}
