package ca.ulaval.glo4002.game.dinosaur.entities.enums;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurDiet.*;

public enum DinosaurSpecies {
    ANKYLOSAURUS("Ankylosaurus", HERBIVORE),
    BRACHIOSAURUS("Brachiosaurus", HERBIVORE),
    DIPLODOCUS("Diplodocus", HERBIVORE),
    STEGOSAURUS("Stegosaurus", HERBIVORE),
    TRICERATOPS("Triceratops", HERBIVORE),
    ALLOSAURUS("Allosaurus", CARNIVORE),
    MEGALOSAURUS("Megalosaurus", CARNIVORE),
    SPINOSAURUS("Spinosaurus", CARNIVORE),
    TYRANNOSAURUS_REX("Tyrannosaurus Rex", CARNIVORE),
    VELOCIRAPTOR("Velociraptor", CARNIVORE),
    EORAPTOR("Eoraptor", OMNIVORE),
    GIGANTORAPTOR("Gigantoraptor", OMNIVORE),
    HETERODONTOSAURUS("Heterodontosaurus", OMNIVORE),
    ORNITHOMIMUS("Ornithomimus", OMNIVORE),
    STRUTHIOMIMUS("Struthiomimus", OMNIVORE);

    private final String species;
    private final DinosaurDiet diet;

    DinosaurSpecies(String species, DinosaurDiet diet) {
        this.species = species;
        this.diet = diet;
    }

    public String toString() {
        return this.species;
    }

    public static DinosaurSpecies getSpeciesFromString(String dinosaurSpecies) {
        for (DinosaurSpecies species : DinosaurSpecies.values()) {
            if (species.toString().equals(dinosaurSpecies)) {
                return species;
            }
        }
        return null;
    }

    public DinosaurDiet toDiet() {
        return diet;
    }
}
