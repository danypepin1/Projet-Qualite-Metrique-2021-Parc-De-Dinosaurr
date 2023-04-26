package ca.ulaval.glo4002.game.sumochallenge.entities;

import java.util.Arrays;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;

public class SumoChallengeAction implements Action {
    private static final String TIE_RESULT = "tie";

    private final SumoChallenge sumoChallenge;
    private final DinosaurRepository dinosaurRepository;

    @Inject
    public SumoChallengeAction(SumoChallenge sumoChallenge, DinosaurRepository dinosaurRepository) {
        this.sumoChallenge = sumoChallenge;
        this.dinosaurRepository = dinosaurRepository;
    }

    public String predictWinner() {
        Dinosaur challenger = dinosaurRepository.findByName(sumoChallenge.getChallenger());
        Dinosaur challengee = dinosaurRepository.findByName(sumoChallenge.getChallengee());
        switch (challenger.compareStrength(challengee)) {
            case STRONGER:
                return sumoChallenge.getChallenger();
            case WEAKER:
                return sumoChallenge.getChallengee();
            case EQUAL:
                return TIE_RESULT;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void execute() {
        Dinosaur challenger = dinosaurRepository.findByName(sumoChallenge.getChallenger());
        Dinosaur challengee = dinosaurRepository.findByName(sumoChallenge.getChallengee());
        challenger.fight(challengee);
        dinosaurRepository.addAll(Arrays.asList(challenger, challengee));
    }

    @Override
    public boolean equals(Object action) {
        if (!(action instanceof SumoChallengeAction)) {
            return false;
        }
        SumoChallengeAction other = (SumoChallengeAction) action;
        return sumoChallenge.equals(other.sumoChallenge);
    }
}
