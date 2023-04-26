package ca.ulaval.glo4002.game.sumochallenge.api;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.sumochallenge.api.assemblers.SumoChallengeDtoAssembler;
import ca.ulaval.glo4002.game.sumochallenge.api.dtos.SumoChallengeRequest;
import ca.ulaval.glo4002.game.sumochallenge.application.SumoChallengeUseCase;
import ca.ulaval.glo4002.game.sumochallenge.application.dtos.SumoChallengeCreationDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SumoChallengeControllerTest {
    private static final String PREDICTED_WINNER = "Jack";

    private SumoChallengeUseCase sumoChallengeUseCase;
    private SumoChallengeController sumoChallengeController;
    private SumoChallengeDtoAssembler sumoChallengeDtoAssembler;
    private SumoChallengeRequest sumoChallengeRequest;
    private SumoChallengeCreationDto sumoChallengeCreationDto;

    @BeforeEach
    void setup() {
        sumoChallengeUseCase = mock(SumoChallengeUseCase.class);
        sumoChallengeDtoAssembler = mock(SumoChallengeDtoAssembler.class);
        sumoChallengeRequest = new SumoChallengeRequest();
        sumoChallengeController = new SumoChallengeController(sumoChallengeUseCase, sumoChallengeDtoAssembler);
        sumoChallengeCreationDto = new SumoChallengeCreationDto();
    }

    @Test
    void givenSumoChallengeRequest_whenCreateSumoChallenge_thenDtoIsAssembledFromRequest() {
        sumoChallengeController.createSumoChallenge(sumoChallengeRequest);

        verify(sumoChallengeDtoAssembler).fromRequest(sumoChallengeRequest);
    }

    @Test
    void givenSumoChallengeRequest_whenCreateSumoChallenge_thenSumoChallengeActionIsCreated() {
        when(sumoChallengeDtoAssembler.fromRequest(sumoChallengeRequest)).thenReturn(sumoChallengeCreationDto);

        sumoChallengeController.createSumoChallenge(sumoChallengeRequest);

        verify(sumoChallengeUseCase).createSumoChallengeAction(sumoChallengeCreationDto);
    }

    @Test
    void givenSumoChallengeRequest_whenCreateSumoChallenge_thenPredictedWinnerIsAssembledToResponse() {
        when(sumoChallengeUseCase.createSumoChallengeAction(any())).thenReturn(PREDICTED_WINNER);

        sumoChallengeController.createSumoChallenge(sumoChallengeRequest);

        verify(sumoChallengeDtoAssembler).toResponse(PREDICTED_WINNER);
    }

    @Test
    void givenSumoChallengeRequest_whenCreateSumoChallenge_thenOkStatusIsReturned() {
        Response response = sumoChallengeController.createSumoChallenge(sumoChallengeRequest);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
}
