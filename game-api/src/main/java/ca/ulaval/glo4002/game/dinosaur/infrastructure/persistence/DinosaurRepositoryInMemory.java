package ca.ulaval.glo4002.game.dinosaur.infrastructure.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DinosaurNotFoundException;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;

public class DinosaurRepositoryInMemory implements DinosaurRepository {
    private final DinosaurFactory factory;

    private final Map<String, Dinosaur> dinosaurByName = new HashMap<>();

    @Inject
    public DinosaurRepositoryInMemory(DinosaurFactory factory) {
        this.factory = factory;
    }

    @Override
    public void clear() {
        dinosaurByName.clear();
    }

    @Override
    public void add(Dinosaur dinosaur) {
        dinosaurByName.put(dinosaur.getName(), factory.create(dinosaur));
    }

    @Override
    public Dinosaur findByName(String name) {
        if (this.contains(name)) {
            return factory.create(dinosaurByName.get(name));
        }
        throw new DinosaurNotFoundException();
    }

    @Override
    public List<Dinosaur> readAll() {
        List<Dinosaur> copies = new ArrayList<>();
        dinosaurByName.values().forEach(dinosaur -> copies.add(factory.create(dinosaur)));
        return copies;
    }

    @Override
    public void addAll(List<Dinosaur> dinosaurs) {
        dinosaurs.forEach(dinosaur -> dinosaurByName.put(dinosaur.getName(), factory.create(dinosaur)));
    }

    @Override
    public boolean contains(String name) {
        return dinosaurByName.containsKey(name);
    }

    @Override
    public void removeAll(List<Dinosaur> dinosaurs) {
        dinosaurs.forEach(d -> dinosaurByName.remove(d.getName()));
    }
}
