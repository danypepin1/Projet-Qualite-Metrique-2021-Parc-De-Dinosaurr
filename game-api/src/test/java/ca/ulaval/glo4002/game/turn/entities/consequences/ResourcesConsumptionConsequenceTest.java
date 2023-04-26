package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.resource.entities.FoodBank;
import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.ALLOSAURUS;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.EORAPTOR;
import static org.mockito.Mockito.*;

class ResourcesConsumptionConsequenceTest {
    private static final String SOME_DINOSAUR_NAME = "Pierre";
    private static final DinosaurSpecies SOME_SPECIES = EORAPTOR;
    private static final DinosaurSpecies SOME_CARNIVORE_SPECIES = ALLOSAURUS;
    private Dinosaur someDinosaur;
    private Dinosaur someStrongerDinosaur;
    private Dinosaur someWeakestDinosaur;
    private ResourceRepository resourceRepository;
    private DinosaurRepository dinosaurRepository;
    private ResourcesConsumptionConsequence consequence;
    private List<Resource> someResources;

    @BeforeEach
    public void setup() {
        resourceRepository = mock(ResourceRepository.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        setupDinosaurs();
        setupResources();
        consequence = new ResourcesConsumptionConsequence(resourceRepository, dinosaurRepository);
    }

    private void setupDinosaurs() {
        someDinosaur = mock(Dinosaur.class);
        someDinosaur.calculateHunger();
        when(someDinosaur.getName()).thenReturn(SOME_DINOSAUR_NAME);
        when(someDinosaur.compareTo(any())).thenReturn(-1);
        when(someDinosaur.getSpecies()).thenReturn(SOME_SPECIES);
        when(someDinosaur.getDiet()).thenReturn(SOME_SPECIES.toDiet());

        someStrongerDinosaur = mock(Dinosaur.class);
        someStrongerDinosaur.calculateHunger();
        when(someStrongerDinosaur.compareTo(any())).thenReturn(1);
        when(someStrongerDinosaur.getSpecies()).thenReturn(SOME_CARNIVORE_SPECIES);
        when(someStrongerDinosaur.getDiet()).thenReturn(SOME_CARNIVORE_SPECIES.toDiet());

        someWeakestDinosaur = mock(Dinosaur.class);
        someWeakestDinosaur.calculateHunger();
        when(someWeakestDinosaur.compareTo(any())).thenReturn(-1);
        when(someWeakestDinosaur.getSpecies()).thenReturn(SOME_SPECIES);
        when(someWeakestDinosaur.getDiet()).thenReturn(SOME_SPECIES.toDiet());

        when(dinosaurRepository.readAll()).thenReturn(
            Arrays.asList(someWeakestDinosaur,someDinosaur, someStrongerDinosaur)
        );
    }
    private void setupResources() {
        someResources = new ArrayList<>();
        when(resourceRepository.readAll()).thenReturn(someResources);
    }

    @Test
    public void givenBurgerConsumerDinosaurs_whenExecute_thenDinosaursConsumeInOrder() {
        consequence.execute();

        InOrder inOrder = inOrder(someStrongerDinosaur, someDinosaur);
        inOrder.verify(someStrongerDinosaur).consume(any(FoodBank.class));
        inOrder.verify(someDinosaur).consume(any(FoodBank.class));
    }

    @Test
    public void givenSaladConsumerDinosaurs_whenExecute_thenDinosaursConsumeInOrder() {
        consequence.execute();

        InOrder inOrder = inOrder(someWeakestDinosaur, someDinosaur);
        inOrder.verify(someWeakestDinosaur).consume(any(FoodBank.class));
        inOrder.verify(someDinosaur).consume(any(FoodBank.class));
    }

    @Test
    public void givenDinosaurs_whenExecute_thenDinosaursAreUpdated() {
        consequence.execute();

        verify(dinosaurRepository).addAll(Arrays.asList(someWeakestDinosaur, someDinosaur, someStrongerDinosaur));
    }

    @Test
    public void givenConsequence_whenExecute_thenUpdatedResourcesAreSaved() {
        consequence.execute();

        verify(resourceRepository, atLeastOnce()).addAll(someResources);
    }
}
