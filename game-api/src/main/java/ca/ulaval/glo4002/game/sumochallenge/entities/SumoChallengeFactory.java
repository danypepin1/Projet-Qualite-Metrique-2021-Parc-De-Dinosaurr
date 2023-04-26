package ca.ulaval.glo4002.game.sumochallenge.entities;

import javax.inject.Inject;

public class SumoChallengeFactory {
    private final SumoChallengeValidator sumoChallengeValidator;

    @Inject
    public SumoChallengeFactory(SumoChallengeValidator sumoChallengeValidator) {
        this.sumoChallengeValidator = sumoChallengeValidator;
    }

    public SumoChallenge create(String challenger, String challengee, int turnNumber) {
        SumoChallenge sumoChallenge = new SumoChallenge(challenger, challengee, turnNumber);
        sumoChallengeValidator.validate(sumoChallenge);
        return sumoChallenge;
    }
}
