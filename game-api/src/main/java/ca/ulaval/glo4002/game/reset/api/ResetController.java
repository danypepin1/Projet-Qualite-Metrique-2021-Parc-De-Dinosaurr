package ca.ulaval.glo4002.game.reset.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.reset.application.ResetUseCase;

@Path("/reset")
@Produces(MediaType.APPLICATION_JSON)
public class ResetController {
    private final ResetUseCase resetUseCase;

    @Inject
    public ResetController(ResetUseCase resetUseCase) {
        this.resetUseCase = resetUseCase;
    }

    @POST
    public Response reset() {
        resetUseCase.reset();
        return Response.status(200).build();
    }
}
