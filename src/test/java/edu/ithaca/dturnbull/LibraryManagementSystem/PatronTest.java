package edu.ithaca.dturnbull.LibraryManagementSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatronTest {
    
    @Test
    void PatronConstructorTest() {
        Library cop = new Library();
        Patron test = new Patron(cop, "Jackson", "password");
        assertEquals("Jackson", test.getName());
        assertEquals(0, test.getId());
        assertEquals("password", test.getPassword());
    }

    @Test
    void multiplePatrongTest() { 
        Library cop = new Library();
        Patron jacksonPatron = new Patron(cop, "Jackson", "password");
        Patron vattanaPatron = new Patron(cop, "Vattana", "password");
        assertEquals("Jackson", jacksonPatron.getName());
        assertEquals(0, jacksonPatron.getId());
        assertEquals("password", jacksonPatron.getPassword());
        assertEquals("Vattana", vattanaPatron.getName());
        assertEquals(1, vattanaPatron.getId());
        assertEquals("password", vattanaPatron.getPassword());
    }

    @Test
    void wishlistAddTest() throws Exception{
        Library lib = new Library();
        Patron jackson = new Patron(lib, "Jackson", "password");
        lib.addBook("Percy Jackson", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 2", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 3", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 4", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 5", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Coding for Dumbies", "Some Guy", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        
        //Ensures that all the example books can be added to a wishlist successfully
        assertTrue(jackson.addToWishlist("Percy Jackson"));
        assertTrue(jackson.addToWishlist("Percy Jackson 2"));
        assertTrue(jackson.addToWishlist("Percy Jackson 3"));
        assertTrue(jackson.addToWishlist("Percy Jackson 4"));
        assertTrue(jackson.addToWishlist("Percy Jackson 5"));
        assertTrue(jackson.addToWishlist("Coding for Dumbies"));

        //Tests to make sure exceptions are thrown if the book title is not found in the library.
        assertThrows(InvalidBookException.class,()->jackson.addToWishlist("Perry Jackson"));
        assertThrows(InvalidBookException.class,()->jackson.addToWishlist("ASDF"));
        //Tests to make sure the same book can't be added to the library at the same time.
        assertThrows(InvalidBookException.class,()->jackson.addToWishlist("Percy Jackson"));

    }
}
