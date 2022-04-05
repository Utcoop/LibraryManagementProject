package edu.ithaca.dturnbull.LibraryManagementSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatronTest {
    @Test
    void PatronConstructorTest() {
        Library lib = new Library();
        Patron test = new Patron(lib, "Jackson", "password");
        assertEquals("Jackson", test.getName());
        assertEquals(0, test.getId());
        assertEquals("password", test.getPassword());
    }

    @Test
    void multiplePatrongTest() { 
        Library lib = new Library();
        Patron jacksonPatron = new Patron(lib, "Jackson", "password");
        Patron vattanaPatron = new Patron(lib, "Vattana", "password");
        assertEquals("Jackson", jacksonPatron.getName());
        assertEquals(0, jacksonPatron.getId());
        assertEquals("password", jacksonPatron.getPassword());
        assertEquals("Vattana", vattanaPatron.getName());
        assertEquals(1, vattanaPatron.getId());
        assertEquals("password", vattanaPatron.getPassword());
    }
}
