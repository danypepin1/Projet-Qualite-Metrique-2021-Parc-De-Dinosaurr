package ca.ulaval.glo4002.game.breed.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeterinaryResponse {
    @JsonProperty
    public String gender;

    @JsonProperty
    public String offspring;

    @JsonProperty
    public String message;
}
