package ca.ulaval.glo4002.game.sumochallenge.entities;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DinosaurNotFoundException;
import ca.ulaval.glo4002.game.sumochallenge.entities.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.sumochallenge.entities.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.sumochallenge.entities.exceptions.MaxCombatsReachedException;

public class SumoChallengeValidator {
    private static final int MAX_NUMBER_OF_CHALLENGES_PER_TURN = 2;

    private final DinosaurRepository dinosaurRepository;
    private final SumoChallengeRepository sumoChallengeRepository;

    @Inject
    public SumoChallengeValidator(
            DinosaurRepository dinosaurRepository, SumoChallengeRepository sumoChallengeRepository
    ) {
        this.dinosaurRepository = dinosaurRepository;
        this.sumoChallengeRepository = sumoChallengeRepository;
    }

    public void validate(SumoChallenge sumoChallenge) {
        validateDinosaurParticipatesOncePerTurn(sumoChallenge);
        validateMaxCombatsPerTurn(sumoChallenge);
        validateNoTRex(sumoChallenge);
        validateParticipantsNames(sumoChallenge);
    }

    private void validateDinosaurParticipatesOncePerTurn(SumoChallenge sumoChallenge) {
        List<SumoChallenge> challenges = sumoChallengeRepository.readAll();
        for (SumoChallenge challenge : challenges) {
            if (challenge.getTurnNumber() == sumoChallenge.getTurnNumber()
                    && (challenge.getChallenger().equals(sumoChallenge.getChallenger())
                        || challenge.getChallengee().equals(sumoChallenge.getChallengee()))) {
                throw new DinosaurAlreadyParticipatingException();
            }
        }
    }

    private void validateMaxCombatsPerTurn(SumoChallenge sumoChallenge) {
        List<SumoChallenge> challenges = sumoChallengeRepository.readAll();
        int numberOfChallengesThisTurn = 0;
        for (SumoChallenge challenge : challenges) {
            if (challenge.getTurnNumber() == sumoChallenge.getTurnNumber()) {
                numberOfChallengesThisTurn++;
            }
        }
        if (numberOfChallengesThisTurn >= MAX_NUMBER_OF_CHALLENGES_PER_TURN) {
            throw new MaxCombatsReachedException();
        }
    }

    private void validateNoTRex(SumoChallenge sumoChallenge) {
        Dinosaur challenger = dinosaurRepository.findByName(sumoChallenge.getChallenger());
        Dinosaur challengee = dinosaurRepository.findByName(sumoChallenge.getChallengee());
        if (challenger.getSpecies() == DinosaurSpecies.TYRANNOSAURUS_REX
                || challengee.getSpecies() == DinosaurSpecies.TYRANNOSAURUS_REX) {
            throw new ArmsTooShortException();
        }
    }

    private void validateParticipantsNames(SumoChallenge sumoChallenge) {
        if (!dinosaurRepository.contains(sumoChallenge.getChallenger())
                || !dinosaurRepository.contains(sumoChallenge.getChallengee())) {
            throw new DinosaurNotFoundException();
        }
    }
}
