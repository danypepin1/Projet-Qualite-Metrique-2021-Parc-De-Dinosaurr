package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

import static org.mockito.Mockito.*;

class DinosaursFastingConsequenceTest {
    private static final String SOME_DINOSAUR_NAME = "Pierre";

    private Dinosaur someDinosaur;
    private DinosaurRepository dinosaurRepository;
    private DinosaursFastingConsequence consequence;

    @BeforeEach
    public void setup() {
        dinosaurRepository = mock(DinosaurRepository.class);
        setupDinosaurs();
        consequence = new DinosaursFastingConsequence(dinosaurRepository);
    }

    public void setupDinosaurs() {
        someDinosaur = mock(Dinosaur.class);
        when(someDinosaur.getName()).thenReturn(SOME_DINOSAUR_NAME);
        when(dinosaurRepository.readAll()).thenReturn(List.of(someDinosaur));
    }

    @Test
    public void givenFastedDinosaur_whenExecute_thenFastedDinosaurIsRemoved() {
        when(someDinosaur.isFasted()).thenReturn(true);

        consequence.execute();

        verify(dinosaurRepository).removeAll(List.of(someDinosaur));
    }

    @Test
    public void givenNotFastedDinosaur_whenExecute_thenDinosaurIsNotRemoved() {
        when(someDinosaur.isFasted()).thenReturn(false);

        consequence.execute();

        verify(dinosaurRepository, never()).removeAll(List.of(someDinosaur));
    }
}
