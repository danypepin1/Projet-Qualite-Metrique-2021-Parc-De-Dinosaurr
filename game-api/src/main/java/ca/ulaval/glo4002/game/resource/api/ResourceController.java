package ca.ulaval.glo4002.game.resource.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.resource.api.assemblers.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.resource.api.assemblers.ResourceInventoryDtoAssembler;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceInventoriesResponse;
import ca.ulaval.glo4002.game.resource.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.resource.application.ResourceUseCase;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.resource.application.dtos.ResourceInventoryDto;

@Path("/resources")
@Produces(MediaType.APPLICATION_JSON)
public class ResourceController {
    private final ResourceUseCase resourceUseCase;
    private final ResourceDtoAssembler resourceDtoAssembler;
    private final ResourceInventoryDtoAssembler resourceInventoryDtoAssembler;

    @Inject
    public ResourceController(
        ResourceUseCase resourceUseCase, ResourceDtoAssembler resourceDtoAssembler,
        ResourceInventoryDtoAssembler resourceInventoryDtoAssembler
    ) {
        this.resourceUseCase = resourceUseCase;
        this.resourceDtoAssembler = resourceDtoAssembler;
        this.resourceInventoryDtoAssembler = resourceInventoryDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResource(ResourceRequest request) {
        ResourceCreationDto dto = resourceDtoAssembler.fromRequest(request);
        resourceUseCase.createResourceAction(dto);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResourceInventory() {
        ResourceInventoryDto dto = resourceUseCase.getResourceInventory();
        ResourceInventoriesResponse response = resourceInventoryDtoAssembler.toResponse(dto);
        return Response.ok().entity(response).build();
    }
}
