package ca.ulaval.glo4002.game.breed.api;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.breed.api.assemblers.BreedDtoAssembler;
import ca.ulaval.glo4002.game.breed.api.dtos.BreedRequest;
import ca.ulaval.glo4002.game.breed.application.BreedUseCase;
import ca.ulaval.glo4002.game.breed.application.dtos.BreedDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BreedControllerTest {
    private BreedUseCase breedUseCase;
    private BreedDtoAssembler breedDtoAssembler;
    private BreedRequest breedRequest;
    private BreedController breedController;
    private BreedDto breedDto;

    @BeforeEach
    public void setUp() {
        breedUseCase = mock(BreedUseCase.class);
        breedDtoAssembler = mock(BreedDtoAssembler.class);
        breedRequest = new BreedRequest();
        breedController = new BreedController(breedUseCase, breedDtoAssembler);
        breedDto = new BreedDto();
    }

    @Test
    public void givenBreedRequest_whenBreedDinosaur_thenCreateBreedActionFromBreedUseCaseIsCalled() {
        when(breedDtoAssembler.fromRequest(breedRequest)).thenReturn(breedDto);

        breedController.breedDinosaur(breedRequest);

        verify(breedUseCase).createBreedAction(breedDto);
    }

    @Test
    public void givenBreedRequest_whenBreedDinosaur_thenReturnsOkStatus() {
        Response response = breedController.breedDinosaur(breedRequest);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void givenBreedRequest_whenBreedDinosaur_thenFromRequestIsCalled() {
        breedController.breedDinosaur(breedRequest);

        verify(breedDtoAssembler).fromRequest(breedRequest);
    }
}
