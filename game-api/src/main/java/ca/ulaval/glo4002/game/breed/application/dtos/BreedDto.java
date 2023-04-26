package ca.ulaval.glo4002.game.breed.application.dtos;

import ca.ulaval.glo4002.game.breed.entities.BreedOrder;

public class BreedDto implements BreedOrder {
    public String babyName;
    public String fatherName;
    public String motherName;

    @Override
    public String getBabyName() {
        return babyName;
    }

    @Override
    public String getFatherName() {
        return fatherName;
    }

    @Override
    public String getMotherName() {
        return motherName;
    }

    @Override
    public boolean equals(Object breedOrder) {
        if (!(breedOrder instanceof BreedOrder)) {
            return false;
        }
        BreedOrder other = (BreedOrder) breedOrder;
        return babyName.equals(other.getBabyName())
            && fatherName.equals(other.getFatherName())
            && motherName.equals(other.getMotherName());
    }
}
