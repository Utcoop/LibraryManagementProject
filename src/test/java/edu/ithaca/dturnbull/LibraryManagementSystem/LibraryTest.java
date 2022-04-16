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
        addBookTest(la);
        removeBookTest(la);
    }
    
    void addBookTest(Library library) throws UnrecognizedException {
    assertEquals(0, la.getBooks().size());

    la.addBook("Harry Potter","Potter","07/20/1960","Sci-Fi",30);
    assertEquals(1, la.getBooks().size());

    la.addBook("Percy Jackson","KennyS","06/19/1990","Sci-Fi",20);
    assertEquals(2, la.getBooks().size());

    la.addBook("OMG","Oh Kay","01/17/2005","Sci-Fi",15);
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
