package ca.ulaval.glo4002.game.resource.application;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.resource.application.assemblers.ResourceAssembler;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;
import ca.ulaval.glo4002.game.resource.entities.Resource;
import ca.ulaval.glo4002.game.resource.entities.ResourceFactory;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResourceUseCaseTest {
    private static final int SOME_FRESH_BURGER_QTY = 234;
    private static final int SOME_FRESH_SALAD_QTY = 324;
    private static final int SOME_FRESH_WATER_QTY = 111;

    private List<Resource> someResources;

    private ResourceRepository resourceRepository;
    private ActionRepository actionRepository;
    private ResourceUseCase resourceUseCase;
    private ResourceAssembler resourceAssembler;

    @BeforeEach
    public void setUp() {
        someResources = Arrays.asList(
            mock(Resource.class), mock(Resource.class), mock(Resource.class)
        );

        resourceRepository = mock(ResourceRepository.class);
        actionRepository = mock(ActionRepository.class);
        resourceAssembler = mock(ResourceAssembler.class);

        resourceUseCase = new ResourceUseCase(
            resourceRepository, mock(TurnRepository.class), actionRepository,
            mock(ResourceFactory.class), resourceAssembler
        );
    }

    @Test
    public void givenUseCase_whenGetResourceInventory_thenAssembledDtoIsReturned() {
        ResourceInventoryDto expected = new ResourceInventoryDto();
        when(resourceRepository.readAll()).thenReturn(someResources);
        when(resourceAssembler.toDto(someResources)).thenReturn(expected);

        ResourceInventoryDto actual = resourceUseCase.getResourceInventory();

        assertEquals(expected, actual);
    }

    @Test
    public void givenResourcesCreationDto_whenCreateResourceAction_thenSaveFromActionRepositoryIsCalled() {
        resourceUseCase.createResourceAction(someResourceCreationDto());

        verify(actionRepository).add(any());
    }

    private ResourceCreationDto someResourceCreationDto() {
        ResourceCreationDto resourceCreationDto = new ResourceCreationDto();

        resourceCreationDto.qtyBurger = SOME_FRESH_BURGER_QTY;
        resourceCreationDto.qtySalad = SOME_FRESH_SALAD_QTY;
        resourceCreationDto.qtyWater = SOME_FRESH_WATER_QTY;

        return resourceCreationDto;
    }
}
