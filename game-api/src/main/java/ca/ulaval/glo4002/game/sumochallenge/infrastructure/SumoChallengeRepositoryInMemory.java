package ca.ulaval.glo4002.game.sumochallenge.infrastructure;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallenge;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeRepository;

public class SumoChallengeRepositoryInMemory implements SumoChallengeRepository {
    private final List<SumoChallenge> sumoChallenges = new ArrayList<>();

    @Override
    public void add(SumoChallenge sumoChallenge) {
        sumoChallenges.add(sumoChallenge);
    }

    @Override
    public List<SumoChallenge> readAll() {
        return new ArrayList<>(sumoChallenges);
    }

    @Override
    public void clear() {
        sumoChallenges.clear();
    }
}
