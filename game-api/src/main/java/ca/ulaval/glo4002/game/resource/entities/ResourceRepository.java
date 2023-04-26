package ca.ulaval.glo4002.game.resource.entities;

import java.util.List;

public interface ResourceRepository {
    List<Resource> readAll();

    void addAll(List<Resource> resources);

    void clear();
}
