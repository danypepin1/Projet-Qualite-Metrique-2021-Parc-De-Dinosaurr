package ca.ulaval.glo4002.game.sumochallenge.entities;

public class SumoChallenge {
    private final String challenger;
    private final String challengee;
    private final int turnNumber;

    public SumoChallenge(String challenger, String challengee, int turnNumber) {
        this.challenger = challenger;
        this.challengee = challengee;
        this.turnNumber = turnNumber;
    }

    public String getChallenger() {
        return challenger;
    }

    public String getChallengee() {
        return challengee;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public boolean equals(Object challenge) {
        if (!(challenge instanceof SumoChallenge)) {
            return false;
        }
        SumoChallenge other = (SumoChallenge) challenge;
        return challenger.equals(other.challenger)
                && challengee.equals(other.challengee)
                && turnNumber == other.turnNumber;
    }
}
