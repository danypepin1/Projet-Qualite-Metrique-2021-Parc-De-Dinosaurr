package ca.ulaval.glo4002.game.dinosaur.entities;

import java.util.List;

public interface DinosaurRepository {
    void clear();

    void add(Dinosaur dinosaur);

    Dinosaur findByName(String name);

    List<Dinosaur> readAll();

    void addAll(List<Dinosaur> dinosaurs);

    boolean contains(String name);

    void removeAll(List<Dinosaur> dinosaurs);
}
