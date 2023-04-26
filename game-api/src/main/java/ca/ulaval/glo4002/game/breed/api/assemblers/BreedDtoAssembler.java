package ca.ulaval.glo4002.game.breed.api.assemblers;

import ca.ulaval.glo4002.game.breed.api.dtos.BreedRequest;
import ca.ulaval.glo4002.game.breed.application.dtos.BreedDto;

public class BreedDtoAssembler {
    public BreedDto fromRequest(BreedRequest breedRequest) {
        BreedDto dto = new BreedDto();
        dto.babyName = breedRequest.name;
        dto.fatherName = breedRequest.fatherName;
        dto.motherName = breedRequest.motherName;
        return dto;
    }
}
