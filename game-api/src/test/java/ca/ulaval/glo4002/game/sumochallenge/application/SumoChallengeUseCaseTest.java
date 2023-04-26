package ca.ulaval.glo4002.game.sumochallenge.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.StrengthDifference;
import ca.ulaval.glo4002.game.sumochallenge.application.dtos.SumoChallengeCreationDto;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallenge;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeAction;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeFactory;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SumoChallengeUseCaseTest {
    private static final String SOME_CHALLENGER = "Godzilla";
    private static final String SOME_CHALLENGEE = "Mothra";
    private static final Integer SOME_TURN_NUMBER = 12;
    private static final StrengthDifference SOME_STRENGTH_DIFFERENCE = StrengthDifference.STRONGER;

    private Dinosaur someDinosaur;

    private SumoChallengeUseCase sumoChallengeUseCase;

    private SumoChallengeCreationDto creationDto;
    private SumoChallengeFactory sumoChallengeFactory;
    private SumoChallenge sumoChallenge;

    private SumoChallengeAction action;

    private ActionRepository actionRepository;
    private TurnRepository turnRepository;
    private DinosaurRepository dinosaurRepository;
    private SumoChallengeRepository sumoChallengeRepository;

    @BeforeEach
    public void setup() {
        setupDinosaur();
        setupSumoChallenge();
        setupRepositories();
        setupAction();

        sumoChallengeUseCase = new SumoChallengeUseCase(sumoChallengeFactory, actionRepository, turnRepository,
                                                        dinosaurRepository, sumoChallengeRepository);
    }

    private void setupDinosaur() {
        someDinosaur = mock(Dinosaur.class);
        when(someDinosaur.compareStrength(someDinosaur)).thenReturn(SOME_STRENGTH_DIFFERENCE);
    }

    private void setupSumoChallenge() {
        creationDto = new SumoChallengeCreationDto();
        creationDto.challenger = SOME_CHALLENGER;
        creationDto.challengee = SOME_CHALLENGEE;

        sumoChallenge = new SumoChallenge(SOME_CHALLENGER, SOME_CHALLENGEE, SOME_TURN_NUMBER);

        sumoChallengeFactory = mock(SumoChallengeFactory.class);
        when(sumoChallengeFactory.create(SOME_CHALLENGER, SOME_CHALLENGEE, SOME_TURN_NUMBER)).thenReturn(sumoChallenge);
    }

    private void setupRepositories() {
        actionRepository = mock(ActionRepository.class);
        turnRepository = mock(TurnRepository.class);
        when(turnRepository.readTurnNumber()).thenReturn(SOME_TURN_NUMBER);
        dinosaurRepository = mock(DinosaurRepository.class);
        when(dinosaurRepository.findByName(anyString())).thenReturn(someDinosaur);
        sumoChallengeRepository = mock(SumoChallengeRepository.class);
    }

    private void setupAction() {
        action = new SumoChallengeAction(sumoChallenge, dinosaurRepository);
    }

    @Test
    public void givenCreationDto_whenCreateSumoChallengeAction_thenChallengeIsCreated() {
        sumoChallengeUseCase.createSumoChallengeAction(creationDto);

        verify(sumoChallengeFactory).create(SOME_CHALLENGER, SOME_CHALLENGEE, SOME_TURN_NUMBER);
    }

    @Test
    public void givenCreationDto_whenCreateSumoChallengeAction_thenChallengeIsSaved() {
        sumoChallengeUseCase.createSumoChallengeAction(creationDto);

        verify(sumoChallengeRepository).add(sumoChallenge);
    }

    @Test
    public void givenCreationDto_whenCreateSumoChallengeAction_thenActionIsSaved() {
        sumoChallengeUseCase.createSumoChallengeAction(creationDto);

        verify(actionRepository).add(action);
    }

    @Test
    public void givenCreationDto_whenCreateSumoChallengeAction_thenPredictedWinnerIsReturned() {
        String predictedWinner = sumoChallengeUseCase.createSumoChallengeAction(creationDto);

        assertEquals(SOME_CHALLENGER, predictedWinner);
    }
}
