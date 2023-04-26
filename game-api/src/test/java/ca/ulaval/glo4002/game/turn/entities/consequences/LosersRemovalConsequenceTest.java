package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

import static org.mockito.Mockito.*;

class LosersRemovalConsequenceTest {
    private LosersRemovalConsequence consequence;
    private Dinosaur someLoserDinosaur;
    private Dinosaur otherLoserDinosaur;
    private Dinosaur someWinnerDinosaur;
    private Dinosaur otherWinnerDinosaur;

    private DinosaurRepository dinosaurRepository;

    @BeforeEach
    public void setup() {
        setupDinosaurs();
        setupDinosaurRepository();
        consequence = new LosersRemovalConsequence(dinosaurRepository);
    }

    private void setupDinosaurs() {
        someLoserDinosaur = mock(Dinosaur.class);
        when(someLoserDinosaur.hasLostChallenge()).thenReturn(true);
        otherLoserDinosaur = mock(Dinosaur.class);
        when(otherLoserDinosaur.hasLostChallenge()).thenReturn(true);
        someWinnerDinosaur = mock(Dinosaur.class);
        when(someWinnerDinosaur.hasLostChallenge()).thenReturn(false);
        otherWinnerDinosaur = mock(Dinosaur.class);
        when(otherWinnerDinosaur.hasLostChallenge()).thenReturn(false);
    }

    private void setupDinosaurRepository() {
        dinosaurRepository = mock(DinosaurRepository.class);
        when(dinosaurRepository.readAll())
            .thenReturn(Arrays.asList(someLoserDinosaur, otherLoserDinosaur, someWinnerDinosaur, otherWinnerDinosaur));
    }

    @Test
    public void givenDinosaurs_whenExecute_thenLosersAreRemoved() {
        consequence.execute();

        verify(dinosaurRepository).removeAll(Arrays.asList(someLoserDinosaur, otherLoserDinosaur));
    }
}
