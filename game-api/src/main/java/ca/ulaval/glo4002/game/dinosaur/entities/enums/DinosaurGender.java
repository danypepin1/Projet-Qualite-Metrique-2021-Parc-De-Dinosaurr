package ca.ulaval.glo4002.game.dinosaur.entities.enums;

public enum DinosaurGender {
    MALE("m"),
    FEMALE("f");

    private final String dinosaurGender;

    DinosaurGender(String dinosaurGender) {
        this.dinosaurGender = dinosaurGender;
    }

    public String toString() {
        return this.dinosaurGender;
    }

    public static DinosaurGender getGenderFromString(String dinosaurGender) {
        for (DinosaurGender gender : DinosaurGender.values()) {
            if (gender.toString().equals(dinosaurGender)) {
                return gender;
            }
        }
        return null;
    }
}
