package ca.ulaval.glo4002.game.reset.api;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.reset.application.ResetUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResetControllerTest {
    private ResetUseCase resetUseCase;
    private ResetController resetController;

    @BeforeEach
    public void setup() {
        resetUseCase = mock(ResetUseCase.class);

        resetController = new ResetController(resetUseCase);
    }

    @Test
    public void givenController_whenExecutingReset_thenResetIsExecutedByUseCase() {
        resetController.reset();

        verify(resetUseCase).reset();
    }

    @Test
    public void givenController_whenExecutingReset_thenReturnsOkStatus() {
        Response response = resetController.reset();

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
}
