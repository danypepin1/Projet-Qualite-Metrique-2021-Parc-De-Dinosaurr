package ca.ulaval.glo4002.game.breed.entities;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DinosaurNotFoundException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidGenderFatherException;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidGenderMotherException;

public class BreedValidator {
    private final DinosaurRepository dinosaurRepository;

    @Inject
    public BreedValidator(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    public void validateBreed(BreedOrder breedOrder) {
        validateBaby(breedOrder.getBabyName());
        validateFather(breedOrder.getFatherName());
        validateMother(breedOrder.getMotherName());
    }

    private void validateBaby(String babyName) {
        if (dinosaurRepository.contains(babyName)) {
            throw new DuplicateNameException();
        }
    }

    private void validateFather(String fatherName) {
        validateParentsName(fatherName);
        validateFathersGender(fatherName);
    }

    private void validateMother(String motherName) {
        validateParentsName(motherName);
        validateMothersGender(motherName);
    }

    private void validateParentsName(String parentName) {
        if (!dinosaurRepository.contains(parentName)) {
            throw new DinosaurNotFoundException();
        }
    }

    private void validateFathersGender(String fatherName) {
        Dinosaur dinosaur = dinosaurRepository.findByName(fatherName);
        if (dinosaur.isFemale()) {
            throw new InvalidGenderFatherException();
        }
    }

    private void validateMothersGender(String motherName) {
        Dinosaur dinosaur = dinosaurRepository.findByName(motherName);
        if (dinosaur.isMale()) {
            throw new InvalidGenderMotherException();
        }
    }
}
