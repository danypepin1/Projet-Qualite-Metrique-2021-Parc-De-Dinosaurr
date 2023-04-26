package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.BabyDinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

import static org.mockito.Mockito.*;

class OrphansRemovalConsequenceTest {
    private static final String SOME_FATHER_NAME = "Jack";
    private static final String SOME_MOTHER_NAME = "Lucie";
    private static final String SOME_BABY_NAME = "Tommy";

    private Dinosaur someFather;
    private Dinosaur someMother;
    private BabyDinosaur someBaby;
    private DinosaurRepository dinosaurRepository;
    private OrphansRemovalConsequence consequence;

    @BeforeEach
    public void setup() {
        dinosaurRepository = mock(DinosaurRepository.class);
        setupDinosaurs();
        consequence = new OrphansRemovalConsequence(dinosaurRepository);
    }

    public void setupDinosaurs() {
        someFather = mock(Dinosaur.class);
        when(someFather.getName()).thenReturn(SOME_FATHER_NAME);

        someMother = mock(Dinosaur.class);
        when(someMother.getName()).thenReturn(SOME_MOTHER_NAME);

        someBaby = mock(BabyDinosaur.class);
        when(someBaby.getName()).thenReturn(SOME_BABY_NAME);
        when(someBaby.isChildOf(SOME_FATHER_NAME)).thenReturn(true);
        when(someBaby.isChildOf(SOME_MOTHER_NAME)).thenReturn(true);
    }

    @Test
    public void givenIntactDinosaurFamily_whenExecute_thenBabyIsNotRemoved() {
        when(dinosaurRepository.readAll()).thenReturn(Arrays.asList(someFather, someMother, someBaby));

        consequence.execute();

        verify(dinosaurRepository, never()).removeAll(List.of(someBaby));
    }

    @Test
    public void givenDinosaurFamilyWithMissingFather_whenExecute_thenBabyIsNotRemoved() {
        when(dinosaurRepository.readAll()).thenReturn(Arrays.asList(someMother, someBaby));

        consequence.execute();

        verify(dinosaurRepository, never()).removeAll(List.of(someBaby));
    }

    @Test
    public void givenDinosaurFamilyWithMissingMother_whenExecute_thenBabyIsNotRemoved() {
        when(dinosaurRepository.readAll()).thenReturn(Arrays.asList(someFather, someBaby));

        consequence.execute();

        verify(dinosaurRepository, never()).removeAll(List.of(someBaby));
    }

    @Test
    public void givenDinosaurFamilyWithBothParentsMissing_whenExecute_thenBabyIsRemoved() {
        when(dinosaurRepository.readAll()).thenReturn(Collections.singletonList(someBaby));

        consequence.execute();

        verify(dinosaurRepository).removeAll(List.of(someBaby));
    }
}
