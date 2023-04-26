package ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrengthConfigurationTest {
    private static final int SOME_WEIGHT = 1000;
    private static final double SOME_DIET_STRENGTH_FACTOR = 0.83;
    private static final double SOME_GENDER_STRENGTH_FACTOR = 1.32;

    @Test
    public void givenConfiguration_whenCalculate_thenCalculatesStrengthCorrectly() {
        StrengthConfiguration strengthConfiguration = new StrengthConfiguration(
            SOME_DIET_STRENGTH_FACTOR, SOME_GENDER_STRENGTH_FACTOR
        );

        int actualValue = strengthConfiguration.calculate(SOME_WEIGHT);

        int expectedValue = (int) Math.ceil(SOME_WEIGHT * SOME_DIET_STRENGTH_FACTOR * SOME_GENDER_STRENGTH_FACTOR);
        assertEquals(expectedValue, actualValue);
    }
}
