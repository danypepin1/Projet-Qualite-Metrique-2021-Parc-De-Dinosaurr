package ca.ulaval.glo4002.game.turn.api.assemblers;

import ca.ulaval.glo4002.game.turn.api.dtos.TurnResponse;

public class TurnDtoAssembler {
    public TurnResponse toResponse(int turnNumber) {
        TurnResponse response = new TurnResponse();
        response.turnNumber = turnNumber;
        return response;
    }
}
