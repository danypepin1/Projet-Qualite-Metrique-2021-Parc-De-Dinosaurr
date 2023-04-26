package ca.ulaval.glo4002.game.breed.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.breed.api.assemblers.BreedDtoAssembler;
import ca.ulaval.glo4002.game.breed.api.dtos.BreedRequest;
import ca.ulaval.glo4002.game.breed.application.BreedUseCase;
import ca.ulaval.glo4002.game.breed.application.dtos.BreedDto;

@Path("/breed")
@Produces(MediaType.APPLICATION_JSON)
public class BreedController {
    private final BreedUseCase breedUseCase;
    private final BreedDtoAssembler breedDtoAssembler;

    @Inject
    public BreedController(BreedUseCase breedUseCase, BreedDtoAssembler breedDtoAssembler) {
        this.breedUseCase = breedUseCase;
        this.breedDtoAssembler = breedDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response breedDinosaur(BreedRequest request) {
        BreedDto dto = breedDtoAssembler.fromRequest(request);
        breedUseCase.createBreedAction(dto);
        return Response.ok().build();
    }
}
