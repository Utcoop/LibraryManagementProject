package edu.ithaca.dturnbull.LibraryManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class IntegrationTest {
    Library mainLibrary = new Library();
    List<Librarian> librarianList = new ArrayList<>();

    public static void RunTest() throws Exception {
        IntegrationTest test = new IntegrationTest();
        test.integrationTest();
    }

    @Test
    void integrationTest() throws Exception {
        LibrarianTest(mainLibrary, librarianList); // test for Librarian Class
        LibraryTest(mainLibrary, librarianList); // test for Library Class
        PatronTest(mainLibrary); // test for Patron Class
        addBookTest(mainLibrary); // Tests Library's add book method
        removeBookTest(mainLibrary); // Tests Library's remove book method
        wishlistTest(mainLibrary);        
    }

    void LibrarianTest(Library mainLibrary, List<Librarian> librarianList) {
        HumanLibrarian librarian1 = new HumanLibrarian(mainLibrary, "Willson Tybur", "123"); // initialize a
                                                                                                           // HumanLibrarian object
        assertEquals("Willson Tybur", librarian1.getName());
        assertEquals(0, librarian1.getId());

        HumanLibrarian librarian2 = new HumanLibrarian(mainLibrary, "Kenny S", "321"); // initialize a HumanLibrarian
                                                                                                     // object
        assertEquals("Kenny S", librarian2.getName());
        assertEquals(1, librarian2.getId());

        HumanLibrarian librarian3 = new HumanLibrarian(mainLibrary, "S1mple", "09890"); // initialize a HumanLibrarian
                                                                                                      // object
        assertEquals("S1mple", librarian3.getName());
        assertEquals(2, librarian3.getId());

        Kiosk kiosk1 = new Kiosk(mainLibrary);
        assertEquals(3, kiosk1.getId()); // initialize a Kiosk object

        Kiosk kiosk2 = new Kiosk(mainLibrary);
        assertEquals(4, kiosk2.getId()); // initialize a Kiosk object

        Kiosk kiosk3 = new Kiosk(mainLibrary);
        assertEquals(5, kiosk3.getId()); // initialize a Kiosk object

        librarianList.add(librarian1);
        librarianList.add(librarian2);
        librarianList.add(librarian3);
        librarianList.add(kiosk1);
        librarianList.add(kiosk2);
        librarianList.add(kiosk3);
        assertEquals(6, librarianList.size());

        // create 5 patrons using librarian1 instance
        librarian1.addPatron("Vattana", "123");
        librarian1.addPatron("Jackson", "321");
        librarian1.addPatron("VZ", "0909");
        librarian1.addPatron("Yelena", "007");
        librarian1.addPatron("Katy", "555");
    }

    void LibraryTest(Library mainLibrary, List<Librarian> librarianList) {
        // add librarians to the mainLibrary object
        for (int i = 0; i < librarianList.size(); i++) {
            mainLibrary.addLibrarian(librarianList.get(i));
        }

        // check if librarians size in the mainLibrary is the same as librarianList size
        assertEquals(librarianList.size(), mainLibrary.getLibrarians().size());

        // check if both lists are pointing to the same objects
        for (int j = 0; j < mainLibrary.getLibrarians().size(); j++) {
            assertEquals(librarianList.get(j), mainLibrary.getLibrarians().get(j));
        }
    }

    void PatronTest(Library mainLibrary) {
        // check if patrons added by HumanLibrarian objects are in the mainLibrary's
        // patron list with correct information
        assertEquals("Vattana", mainLibrary.getPatrons().get(0).getName());
        assertEquals(0, mainLibrary.getPatrons().get(0).getId());

        assertEquals("Jackson", mainLibrary.getPatrons().get(1).getName());
        assertEquals(1, mainLibrary.getPatrons().get(1).getId());

        assertEquals("VZ", mainLibrary.getPatrons().get(2).getName());
        assertEquals(2, mainLibrary.getPatrons().get(2).getId());

        assertEquals("Yelena", mainLibrary.getPatrons().get(3).getName());
        assertEquals(3, mainLibrary.getPatrons().get(3).getId());

        assertEquals("Katy", mainLibrary.getPatrons().get(4).getName());
        assertEquals(4, mainLibrary.getPatrons().get(4).getId());
    }

    void addBookTest(Library library) throws UnrecognizedException {
        assertEquals(0, library.getBooks().size());
        // Adds 3 books
        library.addBook("Harry Potter", "Mark Twain", "123213213", "02-12-2000", "Crime", 30.0, 5);
        assertEquals(1, library.getBooks().size());

        library.addBook("Percy Jackson", "Maddison Beer", "123213213", "02-12-2000", "Crime", 20.0, 10);
        assertEquals(2, library.getBooks().size());

        library.addBook("OMG", "Kenny S", "123213213", "02-12-2000", "Crime", 15.0, 6);
        assertEquals(3, library.getBooks().size());
    }

    void removeBookTest(Library library) {
        // Removes a book
        assertEquals(3, library.getBooks().size());
        library.removeBook("OMG");

        // Fails to remove a book as Harry does not exist in the library
        assertEquals(2, library.getBooks().size());
        library.removeBook("Harry");
    }

    void wishlistTest(Library lib ) throws Exception {
        //Tests to see if a patron can login
        Patron patron = lib.PatronLogin(1,"321");
        //Tests to see if a librarian can login
        Librarian librarian = lib.LibrarianLogin(2, "09890");
        lib.addBook("Percy Jackson", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 2", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 3", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 4", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 5", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Coding for Dumbies", "Some Guy", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);

        // Ensures that all the example books can be added to a wishlist by a patron successfully and that the patron can check the wishlist
        assertTrue(patron.addToWishlist("Percy Jackson"));
        assertEquals("Percy Jackson\n", patron.checkWishlist());
        assertTrue(patron.addToWishlist("Percy Jackson 2"));
        assertEquals("Percy Jackson\nPercy Jackson 2\n", patron.checkWishlist());
        assertTrue(patron.addToWishlist("Percy Jackson 3"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\n", patron.checkWishlist());
        assertTrue(patron.addToWishlist("Percy Jackson 4"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\n", patron.checkWishlist());
        
        // Ensures that all the example books can be added to a wishlist by a librarian successfully and that the librarian can check the wishlist
        assertTrue(librarian.addToWishlist("Percy Jackson 5", patron));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\n",librarian.checkWishlist(patron));
        assertTrue(librarian.addToWishlist("Coding for Dumbies", patron));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\nCoding for Dumbies\n",librarian.checkWishlist(patron));

        // Tests to make sure exceptions are thrown if the book title is not found in
        // the library.
        assertThrows(InvalidBookException.class, () -> patron.addToWishlist("Perry Jackson"));
        assertThrows(InvalidBookException.class, () -> librarian.addToWishlist("ASDF", patron));
        // Tests to make sure the same book can't be in the wishlist twice
        assertThrows(InvalidBookException.class, () -> patron.addToWishlist("Percy Jackson"));
        assertThrows(InvalidBookException.class, () -> librarian.addToWishlist("Percy Jackson", patron));

        //Exception is thrown when the book being removed is not in the wishlist
        assertThrows(InvalidBookException.class, () -> patron.removeFromWishlist("Percy Jackson 6"));
        assertThrows(InvalidBookException.class, () -> patron.removeFromWishlist("Coding for Smarties"));
        assertThrows(InvalidBookException.class, () -> librarian.removeFromWishlist("Perry Johnson", patron));
        
        //Remove from the start of the list by patron and makes sure that both the librarian and patron recieve the change
        assertTrue(patron.removeFromWishlist("Percy Jackson"));
        assertEquals("Percy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\nCoding for Dumbies\n",patron.checkWishlist());
        assertEquals("Percy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\nCoding for Dumbies\n",librarian.checkWishlist(patron));
        
        //Remove from the end of the list by librarian and makes sure that both the librarian and patron recieve the change
        assertTrue(librarian.removeFromWishlist("Coding for Dumbies", patron));
        assertEquals("Percy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\n",patron.checkWishlist());
        assertEquals("Percy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\n",librarian.checkWishlist(patron));

        //Remove from the middle of the list by patron and makes sure that both the librarian and patron recieve the change
        assertTrue(patron.removeFromWishlist("Percy Jackson 3"));
        assertEquals("Percy Jackson 2\nPercy Jackson 4\nPercy Jackson 5\n",patron.checkWishlist());
        assertEquals("Percy Jackson 2\nPercy Jackson 4\nPercy Jackson 5\n",librarian.checkWishlist(patron));
        
        //Remove from the middle of the list by librarian and makes sure that both the librarian and patron recieve the change
        assertTrue(librarian.removeFromWishlist("Percy Jackson 4", patron));
        assertEquals("Percy Jackson 2\nPercy Jackson 5\n",patron.checkWishlist());
        assertEquals("Percy Jackson 2\nPercy Jackson 5\n",librarian.checkWishlist(patron));
        
        //Remove the final two books and makes sure that both the librarian and patron recieve the change
        assertTrue(patron.removeFromWishlist("Percy Jackson 5"));
        assertEquals("Percy Jackson 2\n",patron.checkWishlist());
        assertEquals("Percy Jackson 2\n",librarian.checkWishlist(patron));

        assertTrue(librarian.removeFromWishlist("Percy Jackson 2", patron));
        assertEquals("",patron.checkWishlist());
        assertEquals("",librarian.checkWishlist(patron));

        //Throws an exception when attempting to remove a book from an empy list
        assertThrows(InvalidBookException.class, () -> patron.removeFromWishlist("Percy Jackson"));
        assertThrows(InvalidBookException.class, () -> librarian.removeFromWishlist("Percy Jackson",patron));
    }

    public static void main(String[] args) throws Exception {
        RunTest();
    }

}
