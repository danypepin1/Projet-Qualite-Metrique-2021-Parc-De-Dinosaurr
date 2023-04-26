package ca.ulaval.glo4002.game.sumochallenge.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SumoChallengeFactoryTest {
    private static final String SOME_CHALLENGER = "Godzilla";
    private static final String SOME_CHALLENGEE = "Mothra";
    private static final int SOME_TURN_NUMBER = 21;

    private SumoChallengeValidator sumoChallengeValidator;
    private SumoChallengeFactory sumoChallengeFactory;
    private SumoChallenge sumoChallenge;

    @BeforeEach
    public void setup() {
        sumoChallengeValidator = mock(SumoChallengeValidator.class);
        sumoChallenge = new SumoChallenge(SOME_CHALLENGER, SOME_CHALLENGEE, SOME_TURN_NUMBER);
        sumoChallengeFactory = new SumoChallengeFactory(sumoChallengeValidator);
    }

    @Test
    public void givenChallengeFactory_whenCreate_thenChallengeIsValidated() {
        sumoChallengeFactory.create(SOME_CHALLENGER, SOME_CHALLENGEE, SOME_TURN_NUMBER);

        verify(sumoChallengeValidator).validate(sumoChallenge);
    }
}
