package ca.ulaval.glo4002.game.breed.entities;

import java.util.Optional;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;

public interface Veterinary {
    Optional<Dinosaur> breed(String name, Dinosaur fatherDinosaur, Dinosaur motherDinosaur);
}
