package ca.ulaval.glo4002.game.dinosaur.api;

import ca.ulaval.glo4002.game.dinosaur.api.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaur.api.exceptions.InvalidDinosaurGenderException;
import ca.ulaval.glo4002.game.dinosaur.api.exceptions.InvalidDinosaurSpeciesException;
import ca.ulaval.glo4002.game.dinosaur.api.exceptions.NonPositiveDinosaurWeightException;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;

public class DinosaurRequestValidator {
    public void validate(DinosaurRequest dinosaurRequest) {
        validateWeight(dinosaurRequest);
        validateGender(dinosaurRequest.gender);
        validateSpecies(dinosaurRequest.species);
    }

    private void validateWeight(DinosaurRequest dinosaurRequest) {
        if (dinosaurRequest.weight <= 0) {
            throw new NonPositiveDinosaurWeightException();
        }
    }

    private void validateGender(String dinosaurGender) {
        if (DinosaurGender.getGenderFromString(dinosaurGender) == null) {
            throw new InvalidDinosaurGenderException();
        }
    }

    private void validateSpecies(String dinosaurSpecies) {
        if (DinosaurSpecies.getSpeciesFromString(dinosaurSpecies) == null) {
            throw new InvalidDinosaurSpeciesException();
        }
    }
}
