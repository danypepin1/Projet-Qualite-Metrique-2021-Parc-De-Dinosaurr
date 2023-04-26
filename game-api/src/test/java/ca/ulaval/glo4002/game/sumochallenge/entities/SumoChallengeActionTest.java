package ca.ulaval.glo4002.game.sumochallenge.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.StrengthDifference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SumoChallengeActionTest {
    private static final String TIE_RESULT = "tie";
    private static final String SOME_CHALLENGER = "Godzilla";
    private static final String SOME_CHALLENGEE = "Mothra";
    private static final Integer SOME_TURN_NUMBER = 12;

    private SumoChallengeAction action;
    private Dinosaur challenger;
    private Dinosaur challengee;

    @BeforeEach
    void setup() {
        challenger = mock(Dinosaur.class);
        challengee = mock(Dinosaur.class);

        DinosaurRepository dinosaurRepository = mock(DinosaurRepository.class);
        when(dinosaurRepository.findByName(SOME_CHALLENGER)).thenReturn(challenger);
        when(dinosaurRepository.findByName(SOME_CHALLENGEE)).thenReturn(challengee);

        SumoChallenge sumoChallenge = new SumoChallenge(SOME_CHALLENGER, SOME_CHALLENGEE, SOME_TURN_NUMBER);

        action = new SumoChallengeAction(sumoChallenge, dinosaurRepository);
    }

    @Test
    public void givenStrongerChallenger_whenPredictWinner_thenChallengerIsPredictedWinner() {
        when(challenger.compareStrength(challengee)).thenReturn(StrengthDifference.STRONGER);

        String winner = action.predictWinner();

        assertEquals(SOME_CHALLENGER, winner);
    }

    @Test
    public void givenStrongerChallengee_whenPredictWinner_thenChallengeeIsPredictedWinner() {
        when(challenger.compareStrength(challengee)).thenReturn(StrengthDifference.WEAKER);

        String winner = action.predictWinner();

        assertEquals(SOME_CHALLENGEE, winner);
    }

    @Test
    public void givenTie_whenPredictWinner_thenTieIsPredicted() {
        when(challenger.compareStrength(challengee)).thenReturn(StrengthDifference.EQUAL);

        String winner = action.predictWinner();

        assertEquals(TIE_RESULT, winner);
    }

    @Test
    public void givenAction_whenExecute_thenChallengerFightsChallengee() {
        action.execute();

        verify(challenger).fight(challengee);
    }
}
