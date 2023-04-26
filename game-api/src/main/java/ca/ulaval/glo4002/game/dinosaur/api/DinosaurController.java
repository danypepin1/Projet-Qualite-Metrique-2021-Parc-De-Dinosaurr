package ca.ulaval.glo4002.game.dinosaur.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.dinosaur.api.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.dinosaur.api.assemblers.WeightModificationDtoAssembler;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.WeightModificationRequest;
import ca.ulaval.glo4002.game.dinosaur.application.DinosaurUseCase;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.WeightModificationDto;

@Path("/dinosaurs")
@Produces(MediaType.APPLICATION_JSON)
public class DinosaurController {
    private final DinosaurUseCase dinosaurUseCase;
    private final DinosaurDtoAssembler dinosaurDtoAssembler;
    private final WeightModificationDtoAssembler weightModificationDtoAssembler;

    @Inject
    public DinosaurController(DinosaurUseCase dinosaurUseCase, DinosaurDtoAssembler dinosaurDtoAssembler,
                              WeightModificationDtoAssembler weightModificationDtoAssembler) {
        this.dinosaurUseCase = dinosaurUseCase;
        this.dinosaurDtoAssembler = dinosaurDtoAssembler;
        this.weightModificationDtoAssembler = weightModificationDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDinosaur(DinosaurRequest request) {
        DinosaurCreationDto dto = dinosaurDtoAssembler.fromRequest(request);
        dinosaurUseCase.createDinosaurAction(dto);
        return Response.ok().build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDinosaurByName(@PathParam("name") String name) {
        DinosaurDto dto = dinosaurUseCase.getDinosaurByName(name);
        DinosaurResponse response = dinosaurDtoAssembler.toResponse(dto);
        return Response.ok().entity(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLivingDinosaurs() {
        List<DinosaurDto> dto = dinosaurUseCase.getAllLivingDinosaurs();
        List<DinosaurResponse> response = dinosaurDtoAssembler.toResponse(dto);
        return Response.ok().entity(response).build();
    }

    @PATCH
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyDinosaurWeight(
        @PathParam("name") String name, WeightModificationRequest weightModificationRequest
    ) {
        WeightModificationDto dto = weightModificationDtoAssembler.fromRequest(weightModificationRequest, name);
        dinosaurUseCase.createWeightModificationAction(dto);
        return Response.ok().build();
    }
}
