package ca.ulaval.glo4002.game.breed.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeterinaryRequest {
    @JsonProperty
    public String motherSpecies;

    @JsonProperty
    public String fatherSpecies;
}
