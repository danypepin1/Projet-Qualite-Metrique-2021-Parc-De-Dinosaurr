package ca.ulaval.glo4002.game.turn.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.turn.api.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.turn.api.dtos.TurnResponse;
import ca.ulaval.glo4002.game.turn.application.TurnUseCase;

@Path("turn")
public class TurnController {
    private final TurnUseCase turnUseCase;
    private final TurnDtoAssembler turnDtoAssembler;

    @Inject
    public TurnController(TurnUseCase turnUseCase, TurnDtoAssembler turnDtoAssembler) {
        this.turnUseCase = turnUseCase;
        this.turnDtoAssembler = turnDtoAssembler;
    }

    @POST
    public Response executeTurn() {
        int turnNumber = turnUseCase.executeTurn();
        TurnResponse response = turnDtoAssembler.toResponse(turnNumber);
        return Response.ok().entity(response).build();
    }
}
