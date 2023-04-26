package ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration;

public class StrengthConfiguration {
    private final double dietStrengthFactor;
    private final double genderStrengthFactor;

    public StrengthConfiguration(double dietStrengthFactor, double genderStrengthFactor) {
        this.dietStrengthFactor = dietStrengthFactor;
        this.genderStrengthFactor = genderStrengthFactor;
    }

    public double getDietStrengthFactor() {
        return dietStrengthFactor;
    }

    public double getGenderStrengthFactor() {
        return genderStrengthFactor;
    }

    public int calculate(int weight) {
        return (int) Math.ceil(weight * dietStrengthFactor * genderStrengthFactor);
    }
}
