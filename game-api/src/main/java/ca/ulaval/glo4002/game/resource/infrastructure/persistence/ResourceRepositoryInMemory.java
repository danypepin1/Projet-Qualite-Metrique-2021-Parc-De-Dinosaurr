package ca.ulaval.glo4002.game.resource.infrastructure.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceFactory;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private final ResourceFactory factory;

    private final Map<UUID, Resource> resourceById = new HashMap<>();

    @Inject
    public ResourceRepositoryInMemory(ResourceFactory factory) {
        this.factory = factory;
    }

    public List<Resource> readAll() {
        List<Resource> copies = new ArrayList<>();
        resourceById.values().forEach(resource -> copies.add(factory.create(resource)));
        return copies;
    }

    public void addAll(List<Resource> resources) {
        resources.forEach(resource -> resourceById.put(resource.getId(), factory.create(resource)));
    }

    public void clear() {
        resourceById.clear();
    }
}
