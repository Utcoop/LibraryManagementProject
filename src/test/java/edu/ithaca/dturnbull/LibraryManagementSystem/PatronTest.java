package edu.ithaca.dturnbull.LibraryManagementSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatronTest {
    Library cop = new Library();
    @Test
    void PatronConstructorTest() {
        
        Patron test = new Patron(cop, "Jackson", "password");
        assertEquals("Jackson", test.getName());
        assertEquals(3, test.getId());
        assertEquals("password", test.getPassword());
    }

    @Test
    void multiplePatrongTest() { 
        Library cop = new Library();
        Patron jacksonPatron = new Patron(cop, "Jackson", "password");
        Patron vattanaPatron = new Patron(cop, "Vattana", "password");
        assertEquals("Jackson", jacksonPatron.getName());
        assertEquals(4, jacksonPatron.getId());
        assertEquals("password", jacksonPatron.getPassword());
        assertEquals("Vattana", vattanaPatron.getName());
        assertEquals(5, vattanaPatron.getId());
        assertEquals("password", vattanaPatron.getPassword());
    }

    @Test
    void loginTest(){
        Library lib = new Library();
        //Check that login works for when the correct information is used
        Patron jacksonPatron = new Patron(lib, "Jackson", "password");
        assertTrue(jacksonPatron.login(0, "password"));
        Patron vattanaPatron = new Patron(lib, "Vattana", "password11");
        assertTrue(vattanaPatron.login(1, "password11"));
        Patron VZPatron = new Patron(lib, "V Z", "1drowssap2");
        assertTrue(VZPatron.login(2, "1drowssap2"));
        
        //Check that login does not work and that password is case sensitive
        assertFalse(jacksonPatron.login(0, "Password"));
        assertFalse(vattanaPatron.login(1, "Password11"));
        assertFalse(VZPatron.login(2, "1drowSsap2"));
        // Check that login will not work with the wrong Id number
        assertFalse(jacksonPatron.login(1, "password"));
        assertFalse(vattanaPatron.login(2, "Password11"));
        assertFalse(VZPatron.login(3, "1drowssap2"));
        
    }
}
