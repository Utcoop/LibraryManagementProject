package edu.ithaca.dturnbull.LibraryManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ithaca.dturnbull.LibraryManagementSystem.HumanLibrarian;
import edu.ithaca.dturnbull.LibraryManagementSystem.Library;

public class IntegrationTest {
    Library mainLibrary = new Library();
    List<Librarian> librarianList = new ArrayList<>();

    @Test
    void IntegrationTest() {
        LibrarianTest(mainLibrary,librarianList); //test for Librarian Class
        LibraryTest(mainLibrary,librarianList); //test for Library Class
        PatronTest(mainLibrary,librarianList); //test for Patron Class
    }

    void LibrarianTest(Library mainLibrary, List<Librarian> librarianList) {
        HumanLibrarian librarian1 = new HumanLibrarian(mainLibrary, "Willson Tybur", "123"); //initialize a HumanLibrarian object
        assertEquals("Willson Tybur", librarian1.getName());
        assertEquals(0, librarian1.getId());

        HumanLibrarian librarian2 = new HumanLibrarian(mainLibrary, "Kenny S", "321"); //initialize a HumanLibrarian object
        assertEquals("Kenny S", librarian2.getName());
        assertEquals(1, librarian2.getId());

        HumanLibrarian librarian3 = new HumanLibrarian(mainLibrary, "S1mple", "09890"); //initialize a HumanLibrarian object
        assertEquals("S1mple", librarian3.getName());
        assertEquals(2, librarian3.getId());

        Kiosk kiosk1 = new Kiosk(mainLibrary);
        assertEquals(3, kiosk1.getId()); //initialize a Kiosk object

        Kiosk kiosk2 = new Kiosk(mainLibrary);
        assertEquals(4, kiosk2.getId()); //initialize a Kiosk object

        Kiosk kiosk3 = new Kiosk(mainLibrary);
        assertEquals(5, kiosk3.getId()); //initialize a Kiosk object


        librarianList.add(librarian1);
        librarianList.add(librarian2);
        librarianList.add(librarian3);
        librarianList.add(kiosk1);
        librarianList.add(kiosk2);
        librarianList.add(kiosk3);
        assertEquals(6, librarianList.size());

        //create 5 patrons using librarian1 instance
        librarian1.addPatron("Vattana", "123"); 
        librarian1.addPatron("Jackson", "321");
        librarian1.addPatron("VZ", "0909");
        librarian1.addPatron("Yelena", "007");
        librarian1.addPatron("Katy", "555");
    }

  
    void LibraryTest(Library mainLibrary, List<Librarian> librarianList) {
        //add librarians to the mainLibrary object
        for (int i = 0; i < librarianList.size(); i++) {
            mainLibrary.addLibrarian(librarianList.get(i));  
        }

        //check if librarians size in the mainLibrary is the same as librarianList size
        assertEquals(librarianList.size(), mainLibrary.getLibrarians().size());

        //check if both lists are pointing to the same objects
        for (int j = 0; j < mainLibrary.getLibrarians().size(); j++) {
            assertEquals(librarianList.get(j), mainLibrary.getLibrarians().get(j));
        }
    }

    void PatronTest(Library mainLibrary, List<Librarian> librarianList) {
        //check if patrons added by HumanLibrarian objects are in the mainLibrary's patron list with correct information
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

   


}
