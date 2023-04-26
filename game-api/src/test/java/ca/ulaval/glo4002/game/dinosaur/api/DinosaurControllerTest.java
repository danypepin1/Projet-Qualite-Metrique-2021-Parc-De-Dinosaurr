package ca.ulaval.glo4002.game.dinosaur.api;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.api.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.dinosaur.api.assemblers.WeightModificationDtoAssembler;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.WeightModificationRequest;
import ca.ulaval.glo4002.game.dinosaur.application.DinosaurUseCase;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.WeightModificationDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DinosaurControllerTest {
    private static final String SOME_DINOSAUR_NAME = "Bobby";

    private DinosaurUseCase dinosaurUseCase;
    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private DinosaurController dinosaurController;
    private DinosaurRequest dinosaurRequest;
    private DinosaurCreationDto dinosaurCreationDto;
    private DinosaurDto dinosaurDto;
    private WeightModificationRequest weightModificationRequest;
    private WeightModificationDtoAssembler weightModificationDtoAssembler;
    private WeightModificationDto weightModificationDto;

    @BeforeEach
    public void setup() {
        dinosaurUseCase = mock(DinosaurUseCase.class);
        dinosaurDtoAssembler = mock(DinosaurDtoAssembler.class);
        weightModificationDtoAssembler = mock(WeightModificationDtoAssembler.class);
        dinosaurRequest = new DinosaurRequest();
        dinosaurController = new DinosaurController(dinosaurUseCase, dinosaurDtoAssembler,
            weightModificationDtoAssembler);
        dinosaurCreationDto = new DinosaurCreationDto();
        dinosaurDto = new DinosaurDto();
        weightModificationRequest = new WeightModificationRequest();
        weightModificationDto = new WeightModificationDto();
    }

    @Test
    public void givenDinosaurRequest_whenCreateDinosaur_thenCreateDinosaurActionFromDinosaurUseCaseIsCalled() {
        when(dinosaurDtoAssembler.fromRequest(dinosaurRequest)).thenReturn(dinosaurCreationDto);

        dinosaurController.createDinosaur(dinosaurRequest);

        verify(dinosaurUseCase).createDinosaurAction(dinosaurCreationDto);
    }

    @Test
    public void givenDinosaurRequest_whenCreateDinosaur_thenReturnsOkStatus() {
        Response response = dinosaurController.createDinosaur(dinosaurRequest);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void givenDinosaurRequest_whenCreateDinosaur_thenFromRequestFromDinosaurDtoAssemblerIsCalled() {
        dinosaurController.createDinosaur(dinosaurRequest);

        verify(dinosaurDtoAssembler).fromRequest(dinosaurRequest);
    }

    @Test
    public void givenDinosaurName_whenGetDinosaurByName_thenCreateDtoFromDinosaurUseCaseIsCalled() {
        dinosaurController.getDinosaurByName(dinosaurRequest.name);

        verify(dinosaurUseCase).getDinosaurByName(dinosaurRequest.name);
    }

    @Test
    public void givenExistingDinosaurName_whenGetDinosaurByName_thenReturnOkStatus() {
        Response response = dinosaurController.getDinosaurByName(dinosaurRequest.name);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void givenDinosaurName_whenGetDinosaurByName_thenFromDtoFromDinosaurDtoAssemblerIsCalled() {
        when(dinosaurUseCase.getDinosaurByName(dinosaurRequest.name)).thenReturn(dinosaurDto);

        dinosaurController.getDinosaurByName(dinosaurRequest.name);

        verify(dinosaurDtoAssembler).toResponse(dinosaurDto);
    }

    @Test
    public void givenRequest_whenGetAllLivingDinosaurs_thenReturnOkStatus() {
        Response response = dinosaurController.getAllLivingDinosaurs();

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void givenRequest_whenGetAllLivingDinosaurs_thenGetsLivingDinosaursFromUseCase() {
        dinosaurController.getAllLivingDinosaurs();

        verify(dinosaurUseCase).getAllLivingDinosaurs();
    }

    @Test
    public void givenRequest_whenGetAllLivingDinosaurs_thenReceivedDtoIsConvertedToResponse() {
        when(dinosaurUseCase.getAllLivingDinosaurs()).thenReturn(
            Collections.singletonList(dinosaurDto));

        dinosaurController.getAllLivingDinosaurs();

        verify(dinosaurDtoAssembler).toResponse(Collections.singletonList(dinosaurDto));
    }

    @Test
    public void givenWeightModificationRequest_whenModifyDinosaurWeight_thenReturnsOkStatus() {
        Response response = dinosaurController.modifyDinosaurWeight(SOME_DINOSAUR_NAME, weightModificationRequest);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void givenWeightModificationRequest_whenModifyDinosaurWeight_thenRequestIsConvertedToDto() {
        dinosaurController.modifyDinosaurWeight(SOME_DINOSAUR_NAME, weightModificationRequest);

        verify(weightModificationDtoAssembler).fromRequest(weightModificationRequest, SOME_DINOSAUR_NAME);
    }

    @Test
    public void givenWeightModificationRequest_whenModifyDinosaurWeight_thenModifyWeightActionIsCalled() {
        when(weightModificationDtoAssembler.fromRequest(weightModificationRequest,
            SOME_DINOSAUR_NAME)).thenReturn(weightModificationDto);

        dinosaurController.modifyDinosaurWeight(SOME_DINOSAUR_NAME, weightModificationRequest);

        verify(dinosaurUseCase).createWeightModificationAction(weightModificationDto);
    }
}

