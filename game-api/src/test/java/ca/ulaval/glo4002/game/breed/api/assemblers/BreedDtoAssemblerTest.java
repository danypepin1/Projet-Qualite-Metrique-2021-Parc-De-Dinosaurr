package ca.ulaval.glo4002.game.breed.api.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.breed.api.dtos.BreedRequest;
import ca.ulaval.glo4002.game.breed.application.dtos.BreedDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreedDtoAssemblerTest {
    private static final String SOME_NAME = "Bob";
    private static final String SOME_FATHER_NAME = "Kevin";
    private static final String SOME_MOTHER_NAME = "Charlotte";

    private BreedRequest breedRequest;
    private BreedDtoAssembler breedDtoAssembler;

    @BeforeEach
    public void setup() {
        breedDtoAssembler = new BreedDtoAssembler();

        breedRequest = new BreedRequest();
        breedRequest.name = SOME_NAME;
        breedRequest.fatherName = SOME_FATHER_NAME;
        breedRequest.motherName = SOME_MOTHER_NAME;
    }

    @Test
    public void givenBreedRequest_whenCreateDtoFromRequest_thenDtoMatchesRequest() {
        BreedDto dto = breedDtoAssembler.fromRequest(breedRequest);

        assertEquals(breedRequest.name, dto.babyName);
        assertEquals(breedRequest.fatherName, dto.fatherName);
        assertEquals(breedRequest.motherName, dto.motherName);
    }
}
