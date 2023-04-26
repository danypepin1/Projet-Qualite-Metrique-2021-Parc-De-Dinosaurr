package ca.ulaval.glo4002.game.action.entities;

import java.util.Queue;

public interface ActionRepository {
    void add(Action action);

    Queue<Action> readAll();

    void clear();
}
