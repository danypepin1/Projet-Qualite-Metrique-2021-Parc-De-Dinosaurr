package ca.ulaval.glo4002.game.dinosaur.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.factories.WeightModificationActionFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class WeightModificationActionFactoryTest {
    private WeightModificationActionFactory weightModificationActionFactory;
    private DinosaurRepository dinosaurRepository;
    private WeightModificationOrder weightModificationOrder;
    private DinosaurValidator dinosaurValidator;

    @BeforeEach
    public void setup() {
        dinosaurRepository = mock(DinosaurRepository.class);
        weightModificationOrder = mock(WeightModificationOrder.class);
        dinosaurValidator = mock(DinosaurValidator.class);
        weightModificationActionFactory = new WeightModificationActionFactory(dinosaurValidator, dinosaurRepository);
    }

    @Test
    public void givenFactory_whenCreate_thenOrderIsValidated() {
        weightModificationActionFactory.create(weightModificationOrder);

        verify(dinosaurValidator).validateWeightUpdate(weightModificationOrder);
    }

    @Test
    public void givenFactory_whenCreate_thenReturnCorrectModifyWeightCreationAction() {
        WeightModificationAction expected = new WeightModificationAction(
            dinosaurRepository, weightModificationOrder
        );

        WeightModificationAction actual = weightModificationActionFactory.create(weightModificationOrder);

        assertEquals(expected, actual);
    }
}
