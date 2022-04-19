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
}
