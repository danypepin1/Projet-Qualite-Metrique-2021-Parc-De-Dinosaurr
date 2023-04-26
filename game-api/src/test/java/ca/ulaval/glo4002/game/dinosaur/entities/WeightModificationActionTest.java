package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class WeightModificationActionTest {
    private static final String SOME_DINOSAUR_NAME = "Bobby";
    private static final int SOME_DINOSAUR_WEIGHT = 50;
    private WeightModificationAction weightModificationAction;
    private DinosaurRepository dinosaurRepository;
    private Dinosaur dinosaur;
    private WeightModificationOrder weightModificationOrder;

    @BeforeEach
    public void setUp() {
        dinosaur = mock(Dinosaur.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        weightModificationOrder = mock(WeightModificationOrder.class);
        weightModificationAction = new WeightModificationAction(dinosaurRepository,
            weightModificationOrder);
        when(weightModificationOrder.getWeight()).thenReturn(SOME_DINOSAUR_WEIGHT);
        when(weightModificationOrder.getName()).thenReturn(SOME_DINOSAUR_NAME);
        when(dinosaurRepository.findByName(weightModificationOrder.getName())).thenReturn(dinosaur);
    }

    @Test
    public void givenAction_whenExecute_thenFindByNameFromDinosaurRepositoryIsCalled() {
        weightModificationAction.execute();

        verify(dinosaurRepository).findByName(weightModificationOrder.getName());
    }

    @Test
    public void givenAction_whenExecute_thenGrowFromDinosaurIsCalled() {
        weightModificationAction.execute();

        verify(dinosaur).grow(weightModificationOrder.getWeight());
    }

    @Test
    public void givenAction_whenExecute_thenSaveFromDinosaurRepositoryIsCalled() {
        weightModificationAction.execute();

        verify(dinosaurRepository).add(dinosaur);
    }
}
