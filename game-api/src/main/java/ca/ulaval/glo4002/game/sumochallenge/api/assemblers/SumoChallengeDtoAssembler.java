package ca.ulaval.glo4002.game.sumochallenge.api.assemblers;

import ca.ulaval.glo4002.game.sumochallenge.api.dtos.SumoChallengeRequest;
import ca.ulaval.glo4002.game.sumochallenge.api.dtos.SumoChallengeResponse;
import ca.ulaval.glo4002.game.sumochallenge.application.dtos.SumoChallengeCreationDto;

public class SumoChallengeDtoAssembler {
    public SumoChallengeCreationDto fromRequest(SumoChallengeRequest sumoChallengeRequest) {
        SumoChallengeCreationDto dto = new SumoChallengeCreationDto();
        dto.challenger = sumoChallengeRequest.challenger;
        dto.challengee = sumoChallengeRequest.challengee;
        return dto;
    }

    public SumoChallengeResponse toResponse(String predictedWinner) {
        SumoChallengeResponse response = new SumoChallengeResponse();
        response.predictedWinner = predictedWinner;
        return response;
    }
}
