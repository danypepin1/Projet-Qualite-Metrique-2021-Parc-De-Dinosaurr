package ca.ulaval.glo4002.game.dinosaur.api.assemblers;

import ca.ulaval.glo4002.game.dinosaur.api.dtos.WeightModificationRequest;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.WeightModificationDto;

public class WeightModificationDtoAssembler {
    public WeightModificationDto fromRequest(WeightModificationRequest weightModificationRequest, String name) {
        WeightModificationDto dto = new WeightModificationDto();
        dto.weight = weightModificationRequest.weight;
        dto.name = name;
        return dto;
    }
}
