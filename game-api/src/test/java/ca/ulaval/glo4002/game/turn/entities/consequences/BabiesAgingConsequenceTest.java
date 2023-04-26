package ca.ulaval.glo4002.game.turn.entities.consequences;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.BabyDinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;

import static org.mockito.Mockito.*;

class BabiesAgingConsequenceTest {
    private static final int SOME_BABY_WEIGHT = 42;
    private static final int SOME_ADULT_WEIGHT = 117;
    private static final int GROW_QUANTITY = 33;

    private BabiesAgingConsequence babiesAgingConsequence;
    private DinosaurRepository dinosaurRepository;
    private DinosaurFactory dinosaurFactory;
    private BabyDinosaur babyDinosaur;
    private Dinosaur adultDinosaur;

    @BeforeEach
    public void setup() {
        babyDinosaur = mock(BabyDinosaur.class);
        adultDinosaur = mock(Dinosaur.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        when(dinosaurRepository.readAll()).thenReturn(Collections.singletonList(babyDinosaur));
        dinosaurFactory = mock(DinosaurFactory.class);

        babiesAgingConsequence = new BabiesAgingConsequence(dinosaurRepository, dinosaurFactory);
    }

    @Test
    public void givenBaby_whenExecute_thenBabyGrows() {
        babiesAgingConsequence.execute();

        verify(babyDinosaur).grow(GROW_QUANTITY);
    }

    @Test
    public void givenBabyWithBabyWeight_whenExecute_thenBabyIsSaved() {
        when(babyDinosaur.getWeight()).thenReturn(SOME_BABY_WEIGHT);

        babiesAgingConsequence.execute();

        verify(dinosaurRepository).add(babyDinosaur);
    }

    @Test
    public void givenBabyWithAdultWeight_whenExecute_thenAdultIsSaved() {
        when(babyDinosaur.getWeight()).thenReturn(SOME_ADULT_WEIGHT);
        when(dinosaurFactory.createAdult(babyDinosaur)).thenReturn(adultDinosaur);

        babiesAgingConsequence.execute();

        verify(dinosaurRepository).add(adultDinosaur);
    }
}
