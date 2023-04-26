package ca.ulaval.glo4002.game.sumochallenge.application;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.sumochallenge.application.dtos.SumoChallengeCreationDto;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallenge;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeAction;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeFactory;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

public class SumoChallengeUseCase {
    private final SumoChallengeFactory sumoChallengeFactory;
    private final ActionRepository actionRepository;
    private final TurnRepository turnRepository;
    private final DinosaurRepository dinosaurRepository;
    private final SumoChallengeRepository sumoChallengeRepository;

    @Inject
    public SumoChallengeUseCase(
        SumoChallengeFactory sumoChallengeFactory, ActionRepository actionRepository, TurnRepository turnRepository,
        DinosaurRepository dinosaurRepository, SumoChallengeRepository sumoChallengeRepository
    ) {
        this.sumoChallengeFactory = sumoChallengeFactory;
        this.actionRepository = actionRepository;
        this.turnRepository = turnRepository;
        this.dinosaurRepository = dinosaurRepository;
        this.sumoChallengeRepository = sumoChallengeRepository;
    }

    public String createSumoChallengeAction(SumoChallengeCreationDto dto) {
        SumoChallenge sumoChallenge = sumoChallengeFactory.create(
                dto.challenger, dto.challengee, turnRepository.readTurnNumber()
        );
        sumoChallengeRepository.add(sumoChallenge);

        SumoChallengeAction action = new SumoChallengeAction(sumoChallenge, dinosaurRepository);
        actionRepository.add(action);
        return action.predictWinner();
    }
}
