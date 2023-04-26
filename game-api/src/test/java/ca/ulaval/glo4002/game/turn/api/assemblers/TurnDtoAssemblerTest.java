package ca.ulaval.glo4002.game.turn.api.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.turn.api.dtos.TurnResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TurnDtoAssemblerTest {
    private static final int SOME_TURN_NUMBER = 42;

    private TurnDtoAssembler assembler;

    @BeforeEach
    public void setup() {
        assembler = new TurnDtoAssembler();
    }

    @Test
    public void givenAssembler_whenConvertingToResponse_thenTurnNumbersIsConverted() {
        TurnResponse response = assembler.toResponse(SOME_TURN_NUMBER);

        assertEquals(SOME_TURN_NUMBER, response.turnNumber);
    }
}
