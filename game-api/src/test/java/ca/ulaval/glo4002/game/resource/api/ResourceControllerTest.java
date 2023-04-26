package ca.ulaval.glo4002.game.resource.api;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.resource.api.assemblers.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.resource.api.assemblers.ResourceInventoryDtoAssembler;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceInventoriesResponse;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceInventoryResponse;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.resource.application.ResourceUseCase;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResourceControllerTest {
    private static final int SOME_FRESH_BURGER_QTY = 234;
    private static final int SOME_FRESH_SALAD_QTY = 324;
    private static final int SOME_FRESH_WATER_QTY = 111;

    private static final int SOME_CONSUMED_BURGER_QTY = 63;
    private static final int SOME_CONSUMED_SALAD_QTY = 41;
    private static final int SOME_CONSUMED_WATER_QTY = 277;

    private static final int SOME_EXPIRED_BURGER_QTY = 2;
    private static final int SOME_EXPIRED_SALAD_QTY = 103;
    private static final int SOME_EXPIRED_WATER_QTY = 100;

    private ResourceController resourceController;
    private ResourceUseCase resourceUseCase;
    private ResourceDtoAssembler resourceDtoAssembler;

    private ResourceInventoryDtoAssembler resourceInventoryDtoAssembler;

    private ResourceRequest resourceRequest;
    private ResourceCreationDto resourceCreationDto;

    private ResourceInventoryDto someResourceInventoryDto;

    private ResourceInventoriesResponse someResourceInventoriesResponse;

    @BeforeEach
    public void setUp() {
        resourceUseCase = mock(ResourceUseCase.class);
        resourceDtoAssembler = mock(ResourceDtoAssembler.class);

        resourceInventoryDtoAssembler = mock(ResourceInventoryDtoAssembler.class);

        resourceController = new ResourceController(
                resourceUseCase, resourceDtoAssembler, resourceInventoryDtoAssembler
        );

        resourceRequest = new ResourceRequest();
        resourceCreationDto = new ResourceCreationDto();

        someResourceInventoryDto = new ResourceInventoryDto();
        ResourceInventoryResponse freshResources = new ResourceInventoryResponse(
                SOME_FRESH_BURGER_QTY, SOME_FRESH_SALAD_QTY, SOME_FRESH_WATER_QTY
        );
        ResourceInventoryResponse consumedResources = new ResourceInventoryResponse(
                SOME_CONSUMED_BURGER_QTY, SOME_CONSUMED_SALAD_QTY, SOME_CONSUMED_WATER_QTY
        );
        ResourceInventoryResponse expiredResources = new ResourceInventoryResponse(
                SOME_EXPIRED_BURGER_QTY, SOME_EXPIRED_SALAD_QTY, SOME_EXPIRED_WATER_QTY
        );
        someResourceInventoriesResponse = new ResourceInventoriesResponse(
                freshResources, consumedResources, expiredResources
        );
    }

    @Test
    public void givenRequest_whenGetResourceInventory_thenReceivesDtoFromUseCase() {
        resourceController.getResourceInventory();

        verify(resourceUseCase).getResourceInventory();
    }

    @Test
    public void givenRequest_whenGetResourceInventory_thenDtoIsAssembledToResponse() {
        when(resourceUseCase.getResourceInventory()).thenReturn(someResourceInventoryDto);

        resourceController.getResourceInventory();

        verify(resourceInventoryDtoAssembler).toResponse(someResourceInventoryDto);
    }

    @Test
    public void givenRequest_whenGetResourceInventory_thenInventoriesAreReturnedInResponse() {
        when(resourceUseCase.getResourceInventory()).thenReturn(someResourceInventoryDto);
        when(resourceInventoryDtoAssembler.toResponse(someResourceInventoryDto))
            .thenReturn(someResourceInventoriesResponse);

        Response response = resourceController.getResourceInventory();

        assertEquals(response.getEntity(), someResourceInventoriesResponse);
    }

    @Test
    public void givenRequest_whenGetResourceInventory_thenResponseStatusIsOk() {
        when(resourceUseCase.getResourceInventory()).thenReturn(someResourceInventoryDto);
        when(resourceInventoryDtoAssembler.toResponse(someResourceInventoryDto))
            .thenReturn(someResourceInventoriesResponse);

        Response response = resourceController.getResourceInventory();

        assertEquals(response.getStatusInfo(), Response.Status.OK);
    }

    @Test
    public void givenResourceRequest_whenCreatedResource_thenFromRequestFromResourceDtoAssemblerIsCalled() {
        resourceController.createResource(resourceRequest);

        verify(resourceDtoAssembler).fromRequest(resourceRequest);
    }

    @Test
    public void givenResourceRequest_whenCreatedResource_thenCreationResourceActionFromResourceUseCaseIsCalled() {
        when(resourceDtoAssembler.fromRequest(resourceRequest)).thenReturn(resourceCreationDto);

        resourceController.createResource(resourceRequest);

        verify(resourceUseCase).createResourceAction(resourceCreationDto);
    }

    @Test
    public void givenResourceRequest_whenCreatedResource_thenOkStatusIsReturned() {
        Response response = resourceController.createResource(resourceRequest);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
}
