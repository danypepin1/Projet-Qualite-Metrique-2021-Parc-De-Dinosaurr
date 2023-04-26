package ca.ulaval.glo4002.game.dinosaur.application;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.application.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurCreationAction;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.WeightModificationAction;
import ca.ulaval.glo4002.game.dinosaur.entities.WeightModificationOrder;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurCreationActionFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.WeightModificationActionFactory;

import static org.mockito.Mockito.*;

class DinosaurUseCaseTest {
    private static final String SOME_NAME = "Dino";

    private DinosaurFactory dinosaurFactory;
    private DinosaurRepository dinosaurRepository;
    private DinosaurUseCase dinosaurUseCase;
    private DinosaurCreationDto dinosaurCreationDto;
    private DinosaurCreationActionFactory dinosaurCreationActionFactory;
    private DinosaurCreationAction dinosaurCreationAction;
    private ActionRepository actionRepository;
    private DinosaurAssembler dinosaurAssembler;
    private Dinosaur dinosaur;
    private WeightModificationActionFactory weightModificationActionFactory;
    private WeightModificationAction weightModificationAction;
    private WeightModificationOrder weightModificationOrder;

    @BeforeEach
    public void setUp() {
        dinosaurFactory = mock(DinosaurFactory.class);
        dinosaurRepository = mock(DinosaurRepository.class);
        dinosaurCreationActionFactory = mock(DinosaurCreationActionFactory.class);
        dinosaurCreationAction = mock(DinosaurCreationAction.class);
        dinosaurAssembler = mock(DinosaurAssembler.class);
        actionRepository = mock(ActionRepository.class);
        weightModificationOrder = mock(WeightModificationOrder.class);
        weightModificationActionFactory = mock(WeightModificationActionFactory.class);
        dinosaurUseCase = new DinosaurUseCase(
            dinosaurRepository, dinosaurFactory, actionRepository, dinosaurCreationActionFactory, dinosaurAssembler,
            weightModificationActionFactory);
        dinosaurCreationDto = new DinosaurCreationDto();
        dinosaur = mock(Dinosaur.class);
        weightModificationAction = mock(WeightModificationAction.class);
    }

    @Test
    public void givenDinosaurCreationDto_whenCreateDinosaurAction_thenCreateFromDinosaurActionFactoryIsCalled() {
        dinosaurUseCase.createDinosaurAction(dinosaurCreationDto);

        verify(dinosaurCreationActionFactory).create(
                dinosaurRepository, dinosaurFactory, dinosaurCreationDto.name, dinosaurCreationDto.weight,
                dinosaurCreationDto.gender, dinosaurCreationDto.species
        );
    }

    @Test
    public void givenDinosaurCreationDto_whenCreateDinosaurAction_thenSaveFromActionRepositoryIsCalled() {
        when(dinosaurCreationActionFactory.create(
                dinosaurRepository, dinosaurFactory, dinosaurCreationDto.name, dinosaurCreationDto.weight,
                dinosaurCreationDto.gender, dinosaurCreationDto.species)
        ).thenReturn(dinosaurCreationAction);

        dinosaurUseCase.createDinosaurAction(dinosaurCreationDto);

        verify(actionRepository).add(dinosaurCreationAction);
    }

    @Test
    public void givenDinosaurName_whenGetDinosaurByName_thenReadByNameFromRepositoryIsCalled() {
        dinosaurUseCase.getDinosaurByName(SOME_NAME);

        verify(dinosaurRepository).findByName(SOME_NAME);
    }

    @Test
    public void givenDinosaurName_whenGetDinosaurByName_thenToDtoFromDinosaurAssemblerIsCalled() {
        when(dinosaurRepository.findByName(SOME_NAME)).thenReturn(dinosaur);

        dinosaurUseCase.getDinosaurByName(SOME_NAME);

        verify(dinosaurAssembler).toDto(dinosaur);
    }

    @Test
    public void givenRequest_whenGetAllLivingDinosaurs_thenReadAllIsCalled() {
        dinosaurUseCase.getAllLivingDinosaurs();

        verify(dinosaurRepository).readAll();
    }

    @Test
    public void givenRequest_whenGetAllLivingDinosaurs_thenReceivedDinosaursIsConvertedToDtos() {
        when(dinosaurRepository.readAll()).thenReturn(Collections.singletonList(dinosaur));

        dinosaurUseCase.getAllLivingDinosaurs();

        verify(dinosaurAssembler).toDtos(Collections.singletonList(dinosaur));
    }

    @Test
    public void givenDinosaurDto_whenCreateWeightModificationAction_thenCreateIsCalled() {
        dinosaurUseCase.createWeightModificationAction(weightModificationOrder);

        verify(weightModificationActionFactory).create(weightModificationOrder);
    }

    @Test
    public void givenDinosaurDto_whenCreateWeightModificationAction_thenSaveFromActionRepositoryIsCalled() {
        when(weightModificationActionFactory.create(weightModificationOrder)).thenReturn(weightModificationAction);

        dinosaurUseCase.createWeightModificationAction(weightModificationOrder);

        verify(actionRepository).add(weightModificationAction);
    }
}
