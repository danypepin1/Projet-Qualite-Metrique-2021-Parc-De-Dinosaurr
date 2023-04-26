package ca.ulaval.glo4002.game.dinosaur.application.dtos;

import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;

public class DinosaurCreationDto {
    public String name;
    public int weight;
    public DinosaurGender gender;
    public DinosaurSpecies species;
}
