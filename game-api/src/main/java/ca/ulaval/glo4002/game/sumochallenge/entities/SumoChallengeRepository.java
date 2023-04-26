package ca.ulaval.glo4002.game.sumochallenge.entities;

import java.util.List;

public interface SumoChallengeRepository {
    void add(SumoChallenge sumoChallenge);

    List<SumoChallenge> readAll();

    void clear();
}
