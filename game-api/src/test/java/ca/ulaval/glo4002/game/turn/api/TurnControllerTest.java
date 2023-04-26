package ca.ulaval.glo4002.game.turn.api;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.turn.api.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.turn.api.dtos.TurnResponse;
import ca.ulaval.glo4002.game.turn.application.TurnUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TurnControllerTest {
    private static final int SOME_TURN_NUMBER = 42;
    private TurnResponse turnResponse;
    private TurnController turnController;
    private TurnUseCase turnUseCase;
    private TurnDtoAssembler turnDtoAssembler;

    @BeforeEach
    public void setup() {
        turnResponse = new TurnResponse();
        turnResponse.turnNumber = SOME_TURN_NUMBER;

        turnUseCase = mock(TurnUseCase.class);
        turnDtoAssembler = mock(TurnDtoAssembler.class);

        turnController = new TurnController(turnUseCase, turnDtoAssembler);
    }

    @Test
    public void givenController_whenExecutingTurn_thenTurnIsExecutedByUseCase() {
        turnController.executeTurn();

        verify(turnUseCase).executeTurn();
    }

    @Test
    public void givenController_whenExecutingTurn_thenTurnNumberIsConvertedToResponse() {
        when(turnUseCase.executeTurn()).thenReturn(SOME_TURN_NUMBER);

        turnController.executeTurn();

        verify(turnDtoAssembler).toResponse(SOME_TURN_NUMBER);
    }

    @Test
    public void givenController_whenExecutingTurn_thenOkStatusIsReturned() {
        Response response = turnController.executeTurn();

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void givenController_whenExecutingTurn_thenTurnNumberIsReturned() {
        when(turnUseCase.executeTurn()).thenReturn(SOME_TURN_NUMBER);
        when(turnDtoAssembler.toResponse(SOME_TURN_NUMBER)).thenReturn(turnResponse);

        Response response = turnController.executeTurn();

        assertEquals(SOME_TURN_NUMBER, ((TurnResponse) response.getEntity()).turnNumber);
    }
}
