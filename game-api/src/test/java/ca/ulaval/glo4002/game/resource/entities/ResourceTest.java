package ca.ulaval.glo4002.game.resource.entities;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;
import ca.ulaval.glo4002.game.resource.entities.exceptions.IllegalResourceConsumptionQuantityException;

import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceState.*;
import static ca.ulaval.glo4002.game.resource.entities.enums.ResourceType.BURGER;
import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {
    private static final int SOME_TURN = 32;

    private static final int SOME_BURGER_FRESH_TURN = 35;
    private static final int SOME_BURGER_EXPIRED_TURN = 37;

    private static final int SOME_QUANTITY = 42;

    private static final int SOME_CONSUMED_QUANTITY = 23;
    private static final int SOME_LARGER_QUANTITY = 420;

    private static final ResourceType SOME_TYPE = BURGER;

    @Test
    public void givenPastDueDate_whenExpireIfPastDueDate_thenShouldExpire() {
        Resource resource = new Resource(SOME_QUANTITY, SOME_TURN, BURGER);

        resource.expireIfPastDueDate(SOME_BURGER_EXPIRED_TURN);

        assertEquals(0, resource.getQuantity(FRESH));
        assertEquals(SOME_QUANTITY, resource.getQuantity(EXPIRED));
    }

    @Test
    public void givenFresh_whenExpireIfPastDueDate_thenShouldNotExpire() {
        Resource resource = new Resource(SOME_QUANTITY, SOME_TURN, BURGER);

        resource.expireIfPastDueDate(SOME_BURGER_FRESH_TURN);

        assertEquals(SOME_QUANTITY, resource.getQuantity(FRESH));
        assertEquals(0, resource.getQuantity(EXPIRED));
    }

    @Test
    void givenResource_whenConsumingQuantity_thenQuantityIsDeductedFromFreshQuantity() {
        Resource resource = new Resource(SOME_QUANTITY, SOME_TURN, SOME_TYPE);

        resource.consumeQuantity(SOME_CONSUMED_QUANTITY);

        assertEquals(SOME_QUANTITY - SOME_CONSUMED_QUANTITY, resource.getQuantity(FRESH));
    }

    @Test
    void givenResource_whenConsumingQuantity_thenQuantityAddedToConsumedQuantity() {
        Resource resource = new Resource(SOME_QUANTITY, SOME_TURN, SOME_TYPE);

        resource.consumeQuantity(SOME_CONSUMED_QUANTITY);

        assertEquals(SOME_CONSUMED_QUANTITY, resource.getQuantity(CONSUMED));
    }

    @Test
    public void givenResource_whenConsumingMoreThanWholeQuantity_thenThrows() {
        Resource resource = new Resource(SOME_QUANTITY, SOME_TURN, SOME_TYPE);

        assertThrows(
                IllegalResourceConsumptionQuantityException.class,
                () -> resource.consumeQuantity(SOME_LARGER_QUANTITY)
        );
    }

    @Test
    public void givenResource_whenConsumeLargerQuantity_thenConsumedQuantityIsReturned() {
        Resource resource = new Resource(SOME_QUANTITY, SOME_TURN, SOME_TYPE);

        int consumedQuantity = resource.consumeMaximum(SOME_LARGER_QUANTITY);

        assertEquals(SOME_QUANTITY, consumedQuantity);
    }

    @Test
    public void givenResource_whenConsumeSmallerQuantity_thenConsumedQuantityIsReturned() {
        Resource resource = new Resource(SOME_LARGER_QUANTITY, SOME_TURN, SOME_TYPE);

        int consumedQuantity = resource.consumeMaximum(SOME_QUANTITY);

        assertEquals(SOME_QUANTITY, consumedQuantity);
    }
}
