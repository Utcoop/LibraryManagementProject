package edu.ithaca.dturnbull.LibraryManagementSystem;

import org.junit.jupiter.api.Test;

import edu.ithaca.dturnbull.LibraryManagementSystem.HumanLibrarian;
import edu.ithaca.dturnbull.LibraryManagementSystem.Kiosk;
import edu.ithaca.dturnbull.LibraryManagementSystem.Library;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    static Library la = new Library();

    @Test
    void addAndRemoveBookTest() throws UnrecognizedException {
        //addBookTest(la);
        //removeBookTest(la);
    }
    
    //Library library
    @Test
    void addBookTest() throws UnrecognizedException {
    assertEquals(0, la.getBooks().size());

    la.addBook("Harry Potter","Mark Twain","02-12-2000","Action",30.0);
    assertEquals(1, la.getBooks().size());

    la.addBook("Percy Jackson","Mark Twain","02-12-2000","Action",20.0);
    assertEquals(2, la.getBooks().size());

    la.addBook("OMG","Mark Twain","02-12-2000","Action",15.0);
    assertEquals(3, la.getBooks().size());
    }

    void removeBookTest(Library library) {
    assertEquals(3, la.getBooks().size());
    la.removeBook("OMG");

    assertEquals(2, la.getBooks().size());
    la.removeBook("Harry");

    assertEquals(2, la.getBooks().size());
    la.removeBook("Harry Potter");

    assertEquals(1, la.getBooks().size());
    la.removeBook("Percy Jackson");

    assertEquals(0, la.getBooks().size());
    }

    @Test
    void patronLoginTest() throws InvalidUserException{
        Library lib = new Library();
        //Check that login works for when the correct information is used
        lib.addPatron(new Patron(lib, "Jackson Becker", "password"));
        lib.addPatron(new Patron(lib, "Jamie Woodworth", "Password11"));
        lib.addPatron(new Patron(lib, "Vattana", "1passWord1"));
        lib.addPatron(new Patron(lib, "V Z", "secret Password"));
        //Checks if each user can login about it
        assertTrue(lib.PatronLogin(0, "password"));
        assertTrue(lib.PatronLogin(1, "Password11"));
        assertTrue(lib.PatronLogin(2, "1passWord1"));
        assertTrue(lib.PatronLogin(3, "secret Password"));
        //Checks to see if PatronLogin doesn't accept incorrect passwords
        assertThrows(InvalidUserException.class,()->lib.PatronLogin(0, "Password")); // upper case 
        assertThrows(InvalidUserException.class,()->lib.PatronLogin(0, "password11")); // added digits
        assertThrows(InvalidUserException.class,()->lib.PatronLogin(1, "password11")); // wrong case
        assertThrows(InvalidUserException.class,()->lib.PatronLogin(2, "1password"));// missing number
        assertThrows(InvalidUserException.class,()->lib.PatronLogin(3, "secret  Password")); // additional space press
        //Checks to see if invalid ID numbers are ignored
        assertThrows(InvalidUserException.class,()->lib.PatronLogin(-1, "password")); // negative id
        assertThrows(InvalidUserException.class,()->lib.PatronLogin(1000, "password11")); // Id does not exist within system
    }

    @Test
    void LibrarianLoginTest() throws InvalidUserException{
        Library lib = new Library();
        //Check that login works for when the correct information is used
        lib.addLibrarian(new HumanLibrarian(lib, "Jackson Becker", "password"));
        lib.addLibrarian(new HumanLibrarian(lib, "Jamie Woodworth", "Password11"));
        lib.addLibrarian(new HumanLibrarian(lib, "Vattana", "1passWord1"));
        lib.addLibrarian(new HumanLibrarian(lib, "V Z", "secret Password"));
        //Checks if each user can login about it
        assertTrue(lib.LibrarianLogin(0, "password"));
        assertTrue(lib.LibrarianLogin(1, "Password11"));
        assertTrue(lib.LibrarianLogin(2, "1passWord1"));
        assertTrue(lib.LibrarianLogin(3, "secret Password"));
        //Checks to see if PatronLogin doesn't accept incorrect passwords
        assertThrows(InvalidUserException.class,()->lib.LibrarianLogin(0, "Password")); // upper case 
        assertThrows(InvalidUserException.class,()->lib.LibrarianLogin(0, "password11")); // added digits
        assertThrows(InvalidUserException.class,()->lib.LibrarianLogin(1, "password11")); // wrong case
        assertThrows(InvalidUserException.class,()->lib.LibrarianLogin(2, "1password"));// missing number
        assertThrows(InvalidUserException.class,()->lib.LibrarianLogin(3, "secret  Password")); // additional space press
        //Checks to see if invalid ID numbers are ignored
        assertThrows(InvalidUserException.class,()->lib.LibrarianLogin(-1, "password")); // negative id
        assertThrows(InvalidUserException.class,()->lib.LibrarianLogin(1000, "password11")); // Id does not exist within system
    }
}
