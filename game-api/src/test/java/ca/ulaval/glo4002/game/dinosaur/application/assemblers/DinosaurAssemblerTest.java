package ca.ulaval.glo4002.game.dinosaur.application.assemblers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class DinosaurAssemblerTest {
    private static final String SOME_NAME = "Dino";
    private static final String OTHER_NAME = "Alaric";
    private static final int SOME_WEIGHT = 10;
    private static final DinosaurGender SOME_GENDER = DinosaurGender.MALE;
    private static final DinosaurSpecies SOME_SPECIES = DinosaurSpecies.ANKYLOSAURUS;

    private static List<Dinosaur> dinosaurs = new ArrayList<>();
    private DinosaurAssembler dinosaurAssembler;
    private Dinosaur dinosaur;

    @BeforeEach
    public void setUp() {
        dinosaurAssembler = new DinosaurAssembler();
        dinosaur = new Dinosaur(
            SOME_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES,
            mock(HungerStrategyFactory.class), mock(StrengthConfiguration.class)
        );
        Dinosaur otherDinosaur = new Dinosaur(
            OTHER_NAME, SOME_WEIGHT, SOME_GENDER, SOME_SPECIES,
            mock(HungerStrategyFactory.class), mock(StrengthConfiguration.class)
        );
        dinosaurs = Arrays.asList(dinosaur, otherDinosaur);
    }

    @Test
    public void givenDinosaurEntity_whenCreateDto_thenDtoMatchesRequest() {
        DinosaurDto dinosaurDto = dinosaurAssembler.toDto(dinosaur);

        assertEquals(dinosaur.getName(), dinosaurDto.name);
        assertEquals(dinosaur.getWeight(), dinosaurDto.weight);
        assertEquals(dinosaur.getGender(), dinosaurDto.gender);
        assertEquals(dinosaur.getSpecies(), dinosaurDto.species);
    }

    @Test
    public void givenValidDinosaurs_whenCreatingDinosaursDto_thenFindAllReturnsSameDinosaursDto() {
        List<DinosaurDto> dinosaurDtoList = dinosaurAssembler.toDtos(dinosaurs);

        assertEquals(dinosaurs.get(0).getName(), dinosaurDtoList.get(0).name);
        assertEquals(dinosaurs.get(1).getName(), dinosaurDtoList.get(1).name);
    }
}
