package ca.ulaval.glo4002.game.sumochallenge.entities;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DinosaurNotFoundException;
import ca.ulaval.glo4002.game.sumochallenge.entities.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.sumochallenge.entities.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.sumochallenge.entities.exceptions.MaxCombatsReachedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SumoChallengeValidatorTest {
    private static final String SOME_CHALLENGER_NAME = "Godzilla";
    private static final String SOME_CHALLENGEE_NAME = "Mothra";
    private static final int SOME_TURN_NUMBER = 12;

    private static final String SOME_OTHER_NAME = "Jack";

    private SumoChallenge someSumoChallenge;
    private SumoChallenge sameTurnChallenge;
    private SumoChallenge otherSameTurnChallenge;

    private Dinosaur challenger;
    private Dinosaur challengee;

    private SumoChallengeRepository sumoChallengeRepository;
    private DinosaurRepository dinosaurRepository;
    private SumoChallengeValidator sumoChallengeValidator;

    @BeforeEach
    public void setup() {
        setupDinosaurs();
        setupDinosaurRepository();
        setupSumoChallenges();

        sumoChallengeRepository = mock(SumoChallengeRepository.class);
        sumoChallengeValidator = new SumoChallengeValidator(dinosaurRepository, sumoChallengeRepository);
    }

    private void setupDinosaurs() {
        challenger = mock(Dinosaur.class);
        when(challenger.getSpecies()).thenReturn(DinosaurSpecies.VELOCIRAPTOR);
        challengee = mock(Dinosaur.class);
        when(challengee.getSpecies()).thenReturn(DinosaurSpecies.ALLOSAURUS);
    }

    private void setupDinosaurRepository() {
        dinosaurRepository = mock(DinosaurRepository.class);
        when(dinosaurRepository.findByName(SOME_CHALLENGER_NAME)).thenReturn(challenger);
        when(dinosaurRepository.findByName(SOME_CHALLENGEE_NAME)).thenReturn(challengee);
    }

    private void setupSumoChallenges() {
        someSumoChallenge = new SumoChallenge(SOME_CHALLENGER_NAME, SOME_CHALLENGEE_NAME, SOME_TURN_NUMBER);
        sameTurnChallenge = new SumoChallenge(SOME_OTHER_NAME, SOME_OTHER_NAME, SOME_TURN_NUMBER);
        otherSameTurnChallenge = new SumoChallenge(SOME_OTHER_NAME, SOME_OTHER_NAME, SOME_TURN_NUMBER);
    }

    @Test
    public void givenAlreadyParticipatingDinosaur_whenValidate_thenExceptionIsThrown() {
        when(sumoChallengeRepository.readAll()).thenReturn(Collections.singletonList(someSumoChallenge));

        assertThrows(
            DinosaurAlreadyParticipatingException.class,
            () -> sumoChallengeValidator.validate(someSumoChallenge)
        );
    }

    @Test
    public void givenMaxNumberOfCombatsPerTurn_whenValidate_thenExceptionIsThrown() {
        when(sumoChallengeRepository.readAll()).thenReturn(Arrays.asList(sameTurnChallenge, otherSameTurnChallenge));

        assertThrows(
            MaxCombatsReachedException.class,
            () -> sumoChallengeValidator.validate(someSumoChallenge)
        );
    }

    @Test
    public void givenTRexChallenger_whenValidate_thenExceptionIsThrown() {
        when(challenger.getSpecies()).thenReturn(DinosaurSpecies.TYRANNOSAURUS_REX);

        assertThrows(
            ArmsTooShortException.class,
            () -> sumoChallengeValidator.validate(someSumoChallenge)
        );
    }

    @Test
    public void givenTRexChallengee_whenValidate_thenExceptionIsThrown() {
        when(challengee.getSpecies()).thenReturn(DinosaurSpecies.TYRANNOSAURUS_REX);

        assertThrows(
            ArmsTooShortException.class,
            () -> sumoChallengeValidator.validate(someSumoChallenge)
        );
    }

    @Test
    public void givenChallengerDoesNotExist_whenValidate_thenExceptionIsThrown() {
        when(dinosaurRepository.contains(SOME_CHALLENGER_NAME)).thenReturn(false);
        when(dinosaurRepository.contains(SOME_CHALLENGEE_NAME)).thenReturn(true);

        assertThrows(
            DinosaurNotFoundException.class,
            () -> sumoChallengeValidator.validate(someSumoChallenge)
        );
    }

    @Test
    public void givenChallengeeDoesNotExist_whenValidate_thenExceptionIsThrown() {
        when(dinosaurRepository.contains(SOME_CHALLENGER_NAME)).thenReturn(true);
        when(dinosaurRepository.contains(SOME_CHALLENGEE_NAME)).thenReturn(false);

        assertThrows(
            DinosaurNotFoundException.class,
            () -> sumoChallengeValidator.validate(someSumoChallenge)
        );
    }

    @Test
    public void givenValidSumoChallenge_whenValidate_thenNoExceptionIsThrown() {
        when(dinosaurRepository.contains(SOME_CHALLENGER_NAME)).thenReturn(true);
        when(dinosaurRepository.contains(SOME_CHALLENGEE_NAME)).thenReturn(true);

        assertDoesNotThrow(() -> sumoChallengeValidator.validate(someSumoChallenge));
    }
}
