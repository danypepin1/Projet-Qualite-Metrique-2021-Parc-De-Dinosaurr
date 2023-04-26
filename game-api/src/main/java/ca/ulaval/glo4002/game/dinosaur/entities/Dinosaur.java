package ca.ulaval.glo4002.game.dinosaur.entities;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.StrengthDifference;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfiguration;
import ca.ulaval.glo4002.game.resource.entities.FoodBank;

public class Dinosaur implements Comparable<Dinosaur> {
    private final String name;
    private int weight;
    private final DinosaurGender gender;
    private final DinosaurSpecies species;
    private boolean isStarving;
    private boolean isFasted;
    private Hunger hunger;
    private boolean hasLostChallenge;

    private final HungerStrategyFactory hungerStrategyFactory;
    private final StrengthConfiguration strengthConfiguration;

    public Dinosaur(
            String name, Integer weight, DinosaurGender gender, DinosaurSpecies species,
            HungerStrategyFactory hungerStrategyFactory, StrengthConfiguration strengthConfiguration
    ) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        isStarving = true;
        isFasted = false;
        hasLostChallenge = false;
        this.hungerStrategyFactory = hungerStrategyFactory;
        this.strengthConfiguration = strengthConfiguration;
    }

    public Dinosaur(Dinosaur other) {
        name = other.name;
        weight = other.weight;
        gender = other.gender;
        species = other.species;
        isStarving = other.isStarving;
        isFasted = other.isFasted;
        hunger = other.hunger;
        hasLostChallenge = other.hasLostChallenge;
        hungerStrategyFactory = other.hungerStrategyFactory;
        strengthConfiguration = other.strengthConfiguration;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public DinosaurGender getGender() {
        return gender;
    }

    public DinosaurSpecies getSpecies() {
        return species;
    }

    public DinosaurDiet getDiet() {
        return species.toDiet();
    }

    public boolean isStarving() {
        return isStarving;
    }

    public boolean isFasted() {
        return isFasted;
    }

    public void calculateHunger() {
        hunger = hungerStrategyFactory.create(species, weight, isStarving).calculate();
    }

    public void consume(FoodBank foodBank) {
        hunger.consume(foodBank);
    }

    public void updateFedStatus() {
        isFasted = !hunger.isDepleted();
        isStarving = false;
    }

    public void grow(int weightDelta) {
        weight = weight + weightDelta;
    }

    public boolean isMale() {
        return DinosaurGender.MALE.equals(gender);
    }

    public boolean isFemale() {
        return DinosaurGender.FEMALE.equals(gender);
    }

    public StrengthDifference compareStrength(Dinosaur other) {
        if (strength() > other.strength()) {
            return StrengthDifference.STRONGER;
        }
        if (strength() < other.strength()) {
            return StrengthDifference.WEAKER;
        }
        return StrengthDifference.EQUAL;
    }

    private int strength() {
        return strengthConfiguration.calculate(weight);
    }

    public boolean hasLostChallenge() {
        return hasLostChallenge;
    }

    public void fight(Dinosaur other) {
        switch (compareStrength(other)) {
            case STRONGER:
                isStarving = true;
                other.hasLostChallenge = true;
                break;
            case WEAKER:
                hasLostChallenge = true;
                other.isStarving = true;
                break;
            case EQUAL:
                isStarving = true;
                other.isStarving = true;
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public int compareTo(Dinosaur other) {
        if (strength() == other.strength()) {
            return other.name.compareTo(name);
        } else {
            return strength() - other.strength();
        }
    }

    @Override
    public boolean equals(Object dinosaur) {
        if (!(dinosaur instanceof Dinosaur)) {
            return false;
        }
        Dinosaur other = (Dinosaur) dinosaur;
        return name.equals(other.name);
    }
}
