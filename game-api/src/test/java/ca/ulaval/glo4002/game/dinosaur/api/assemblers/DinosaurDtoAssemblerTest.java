package ca.ulaval.glo4002.game.dinosaur.api.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.api.DinosaurRequestValidator;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DinosaurDtoAssemblerTest {
    private static final String SOME_NAME = "Bob";
    private static final String OTHER_NAME = "Alice";
    private static final int SOME_WEIGHT = 100;
    private static final String SOME_GENDER = "f";
    private static final String SOME_SPECIES = "Ankylosaurus";
    private static final List<DinosaurDto> dinosaursDtos = new ArrayList<>(5);

    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private DinosaurRequestValidator dinosaurRequestValidator;
    private DinosaurRequest dinosaurRequest;
    private DinosaurDto dinosaurDto;

    @BeforeEach
    public void setup() {
        dinosaurRequestValidator = mock(DinosaurRequestValidator.class);
        dinosaurDtoAssembler = new DinosaurDtoAssembler(dinosaurRequestValidator);

        dinosaurRequest = new DinosaurRequest();
        dinosaurRequest.gender = SOME_GENDER;
        dinosaurRequest.name = SOME_NAME;
        dinosaurRequest.species = SOME_SPECIES;
        dinosaurRequest.weight = SOME_WEIGHT;

        dinosaurDto = new DinosaurDto();
        dinosaurDto.gender = DinosaurGender.getGenderFromString(SOME_GENDER);
        dinosaurDto.name = SOME_NAME;
        dinosaurDto.species = DinosaurSpecies.getSpeciesFromString(SOME_SPECIES);
        dinosaurDto.weight = SOME_WEIGHT;

        DinosaurDto otherDinosaurDto = new DinosaurDto();
        otherDinosaurDto.gender = DinosaurGender.getGenderFromString(SOME_GENDER);
        otherDinosaurDto.name = OTHER_NAME;
        otherDinosaurDto.species = DinosaurSpecies.getSpeciesFromString(SOME_SPECIES);
        otherDinosaurDto.weight = SOME_WEIGHT;

        dinosaursDtos.add(dinosaurDto);
        dinosaursDtos.add(otherDinosaurDto);
    }

    @Test
    public void givenDinosaurRequest_whenCreateDtoFromRequest_thenDtoMatchesRequest() {
        DinosaurCreationDto dto = dinosaurDtoAssembler.fromRequest(dinosaurRequest);

        assertEquals(dinosaurRequest.name, dto.name);
        assertEquals(dinosaurRequest.weight, dto.weight);
        assertEquals(dinosaurRequest.gender, dto.gender.toString());
        assertEquals(dinosaurRequest.species, dto.species.toString());
    }

    @Test
    public void givenDinosaurRequest_whenCreateDtoFromRequest_thenValidateFromDinosaurRequestValidatorIsCalled() {
        dinosaurDtoAssembler.fromRequest(dinosaurRequest);

        verify(dinosaurRequestValidator).validate(dinosaurRequest);
    }

    @Test
    public void givenDinosaurDto_whenCreateResponseFromDto_thenResponseMatchesDto() {
        DinosaurResponse response = dinosaurDtoAssembler.toResponse(dinosaurDto);

        assertEquals(dinosaurDto.name, response.name);
        assertEquals(dinosaurDto.weight, response.weight);
        assertEquals(dinosaurDto.gender.toString(), response.gender);
        assertEquals(dinosaurDto.species.toString(), response.species);
    }

    @Test
    public void givenDinosaursDto_whenCreateResponseFromDto_thenResponseEnumeratesDinosaurs() {
        List<DinosaurResponse> response = dinosaurDtoAssembler.toResponse(dinosaursDtos);

        assertEquals(dinosaursDtos.get(0).name, response.get(0).name);
        assertEquals(dinosaursDtos.get(1).name, response.get(1).name);
    }
}
