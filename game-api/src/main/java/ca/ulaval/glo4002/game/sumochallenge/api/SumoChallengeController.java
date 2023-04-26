package ca.ulaval.glo4002.game.sumochallenge.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.sumochallenge.api.assemblers.SumoChallengeDtoAssembler;
import ca.ulaval.glo4002.game.sumochallenge.api.dtos.SumoChallengeRequest;
import ca.ulaval.glo4002.game.sumochallenge.api.dtos.SumoChallengeResponse;
import ca.ulaval.glo4002.game.sumochallenge.application.SumoChallengeUseCase;
import ca.ulaval.glo4002.game.sumochallenge.application.dtos.SumoChallengeCreationDto;

@Path("/sumodino")
@Produces(MediaType.APPLICATION_JSON)
public class SumoChallengeController {
    private final SumoChallengeUseCase sumoChallengeUseCase;
    private final SumoChallengeDtoAssembler sumoChallengeDtoAssembler;

    @Inject
    public SumoChallengeController(
            SumoChallengeUseCase sumoChallengeUseCase, SumoChallengeDtoAssembler sumoChallengeDtoAssembler
    ) {
        this.sumoChallengeUseCase = sumoChallengeUseCase;
        this.sumoChallengeDtoAssembler = sumoChallengeDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSumoChallenge(SumoChallengeRequest request) {
        SumoChallengeCreationDto dto = sumoChallengeDtoAssembler.fromRequest(request);
        String predictedWinner = sumoChallengeUseCase.createSumoChallengeAction(dto);
        SumoChallengeResponse response = sumoChallengeDtoAssembler.toResponse(predictedWinner);
        return Response.ok().entity(response).build();
    }
}
