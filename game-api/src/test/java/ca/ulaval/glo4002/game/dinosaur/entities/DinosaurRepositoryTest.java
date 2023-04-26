package ca.ulaval.glo4002.game.dinosaur.entities;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DinosaurNotFoundException;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaur.infrastructure.persistence.DinosaurRepositoryInMemory;

import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.ANKYLOSAURUS;
import static ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies.TYRANNOSAURUS_REX;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DinosaurRepositoryTest {
    private static final String SOME_NAME = "Bob";
    private static final String SOME_OTHER_NAME = "Bobby";
    private static final String SOME_UNSAVED_NAME = "Dino";

    private Dinosaur someDinosaur;
    private Dinosaur someUpdatedDinosaur;
    private Dinosaur someOtherDinosaur;

    private DinosaurRepository dinosaurRepository;

    @BeforeEach
    public void setup() {
        someDinosaur = mock(Dinosaur.class);
        when(someDinosaur.getName()).thenReturn(SOME_NAME);
        when(someDinosaur.getSpecies()).thenReturn(ANKYLOSAURUS);

        someUpdatedDinosaur = mock(Dinosaur.class);
        when(someUpdatedDinosaur.getName()).thenReturn(SOME_NAME);
        when(someUpdatedDinosaur.getSpecies()).thenReturn(TYRANNOSAURUS_REX);

        someOtherDinosaur = mock(Dinosaur.class);

        DinosaurFactory dinosaurFactory = mock(DinosaurFactory.class);
        when(dinosaurFactory.create(someDinosaur)).thenReturn(someDinosaur);
        when(dinosaurFactory.create(someUpdatedDinosaur)).thenReturn(someUpdatedDinosaur);
        when(dinosaurFactory.create(someOtherDinosaur)).thenReturn(someOtherDinosaur);

        dinosaurRepository = new DinosaurRepositoryInMemory(dinosaurFactory);
    }

    @Test
    public void givenDinosaur_whenSave_thenDinosaurIsSaved() {
        dinosaurRepository.add(someDinosaur);

        assertTrue(dinosaurRepository.contains(SOME_NAME));
    }

    @Test
    public void givenNameThatIsNotSaved_whenContains_thenDinosaurIsNotFound() {
        boolean isFound = dinosaurRepository.contains(SOME_OTHER_NAME);

        assertFalse(isFound);
    }

    @Test
    public void givenSavedDinosaur_whenClear_thenRepositoryIsEmpty() {
        dinosaurRepository.add(someDinosaur);

        dinosaurRepository.clear();

        assertFalse(dinosaurRepository.contains(SOME_NAME));
    }

    @Test
    public void givenSavedDinosaurs_whenReadAll_thenDinosaursAreReturned() {
        dinosaurRepository.add(someDinosaur);
        dinosaurRepository.add(someOtherDinosaur);

        List<Dinosaur> foundDinosaurs = dinosaurRepository.readAll();

        assertTrue(foundDinosaurs.contains(someDinosaur));
        assertTrue(foundDinosaurs.contains(someOtherDinosaur));
    }

    @Test
    public void givenSavedDinosaur_whenUpdateAll_thenDinosaurIsUpdated() {
        dinosaurRepository.add(someDinosaur);

        dinosaurRepository.addAll(Collections.singletonList(someUpdatedDinosaur));

        Dinosaur foundDinosaur = dinosaurRepository.readAll().get(0);
        assertEquals(someUpdatedDinosaur, foundDinosaur);
    }

    @Test
    public void givenNameThatIsSaved_whenReadByName_thenDinosaurIsFound() {
        dinosaurRepository.add(someDinosaur);

        Dinosaur dinosaur = dinosaurRepository.findByName(SOME_NAME);

        assertEquals(someDinosaur, dinosaur);
    }

    @Test
    public void givenNameThatIsNotSaved_whenReadByName_thenDinosaurIsNotFoundAndThrowException() {
        assertThrows(
                DinosaurNotFoundException.class,
                () -> dinosaurRepository.findByName(SOME_UNSAVED_NAME)
        );
    }

    @Test
    public void givenSavedDinosaur_whenRemovingDinosaur_thenDinosaurIsRemoved() {
        dinosaurRepository.add(someDinosaur);

        dinosaurRepository.removeAll(List.of(someDinosaur));

        assertThrows(
                DinosaurNotFoundException.class,
                () -> dinosaurRepository.findByName(SOME_NAME)
        );
    }
}
