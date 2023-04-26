package ca.ulaval.glo4002.game.resource.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.entities.enums.ResourceType;
import ca.ulaval.glo4002.game.resource.infrastructure.persistence.ResourceRepositoryInMemory;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourceRepositoryTest {
    private static final int SOME_RESOURCE_QUANTITY = 48;
    private static final int SOME_RESOURCE_CREATION_TURN = 75;
    private static final int OTHER_RESOURCE_QUANTITY = 62;
    private static final int OTHER_RESOURCE_CREATION_TURN = 745;

    private Resource someResource;
    private Resource someOtherResource;
    private List<Resource> someResources;
    private ResourceRepository repository;

    @BeforeEach
    public void setup() {
        someResource = new Resource(SOME_RESOURCE_QUANTITY, SOME_RESOURCE_CREATION_TURN, ResourceType.BURGER);
        someOtherResource = new Resource(OTHER_RESOURCE_QUANTITY, OTHER_RESOURCE_CREATION_TURN, ResourceType.WATER);
        someResources = new ArrayList<>(Arrays.asList(someResource, someOtherResource));

        repository = new ResourceRepositoryInMemory(new ResourceFactory());
    }

    @Test
    public void givenResources_whenSaveAll_thenResourcesAreSaved() {
        repository.addAll(someResources);

        List<Resource> resources = repository.readAll();
        assertTrue(resources.contains(someResource));
        assertTrue(resources.contains(someOtherResource));
    }

    @Test
    public void givenAddedResources_whenClear_thenResourcesAreCleared() {
        repository.addAll(someResources);

        repository.clear();

        assertTrue(repository.readAll().isEmpty());
    }
}
