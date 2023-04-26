package ca.ulaval.glo4002.game.dinosaur.api.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.api.DinosaurRequestValidator;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;

public class DinosaurDtoAssembler {
    private final DinosaurRequestValidator dinosaurRequestValidator;

    @Inject
    public DinosaurDtoAssembler(DinosaurRequestValidator dinosaurRequestValidator) {
        this.dinosaurRequestValidator = dinosaurRequestValidator;
    }

    public DinosaurCreationDto fromRequest(DinosaurRequest dinosaurRequest) {
        dinosaurRequestValidator.validate(dinosaurRequest);

        DinosaurCreationDto dto = new DinosaurCreationDto();
        dto.name = dinosaurRequest.name;
        dto.weight = dinosaurRequest.weight;
        dto.gender = DinosaurGender.getGenderFromString(dinosaurRequest.gender);
        dto.species = DinosaurSpecies.getSpeciesFromString(dinosaurRequest.species);
        return dto;
    }

    public DinosaurResponse toResponse(DinosaurDto dinosaurDto) {
        DinosaurResponse response = new DinosaurResponse();
        response.name = dinosaurDto.name;
        response.weight = dinosaurDto.weight;
        response.gender = dinosaurDto.gender.toString();
        response.species = dinosaurDto.species.toString();
        return response;
    }

    public List<DinosaurResponse> toResponse(List<DinosaurDto> dinosaursDtos) {
        return dinosaursDtos.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
