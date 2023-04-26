package ca.ulaval.glo4002.game.action.infrastructure.persistence;

import java.util.LinkedList;
import java.util.Queue;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.action.entities.ActionRepository;

public class ActionRepositoryInMemory implements ActionRepository {
    private final Queue<Action> actions = new LinkedList<>();

    @Override
    public void add(Action action) {
        actions.add(action);
    }

    @Override
    public Queue<Action> readAll() {
        return new LinkedList<>(actions);
    }

    @Override
    public void clear() {
        actions.clear();
    }
}
