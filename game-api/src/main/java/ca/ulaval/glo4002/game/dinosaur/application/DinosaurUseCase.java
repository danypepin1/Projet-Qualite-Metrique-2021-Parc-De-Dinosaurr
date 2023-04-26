package ca.ulaval.glo4002.game.dinosaur.application;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.action.entities.Action;
import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.dinosaur.application.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.WeightModificationOrder;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurCreationActionFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.WeightModificationActionFactory;

public class DinosaurUseCase {
    private final DinosaurFactory dinosaurFactory;
    private final DinosaurRepository dinosaurRepository;
    private final ActionRepository actionRepository;
    private final DinosaurCreationActionFactory dinosaurCreationActionFactory;
    private final DinosaurAssembler dinosaurAssembler;
    private final WeightModificationActionFactory weightModificationActionFactory;

    @Inject
    public DinosaurUseCase(
        DinosaurRepository dinosaurRepository, DinosaurFactory dinosaurFactory,
        ActionRepository actionRepository, DinosaurCreationActionFactory dinosaurCreationActionFactory,
        DinosaurAssembler dinosaurAssembler, WeightModificationActionFactory weightModificationActionFactory
    ) {
        this.dinosaurRepository = dinosaurRepository;
        this.dinosaurFactory = dinosaurFactory;
        this.actionRepository = actionRepository;
        this.dinosaurCreationActionFactory = dinosaurCreationActionFactory;
        this.dinosaurAssembler = dinosaurAssembler;
        this.weightModificationActionFactory = weightModificationActionFactory;
    }

    public void createDinosaurAction(DinosaurCreationDto dto) {
        Action dinosaurAction = dinosaurCreationActionFactory.create(
                dinosaurRepository, dinosaurFactory, dto.name, dto.weight, dto.gender, dto.species
        );
        actionRepository.add(dinosaurAction);
    }

    public DinosaurDto getDinosaurByName(String name) {
        Dinosaur dinosaur = dinosaurRepository.findByName(name);
        return dinosaurAssembler.toDto(dinosaur);
    }

    public List<DinosaurDto> getAllLivingDinosaurs() {
        List<Dinosaur> dinosaurs = dinosaurRepository.readAll();
        return dinosaurAssembler.toDtos(dinosaurs);
    }

    public void createWeightModificationAction(WeightModificationOrder weightModificationOrder) {
        Action weightModificationAction = weightModificationActionFactory.create(weightModificationOrder);
        actionRepository.add(weightModificationAction);
    }
}
