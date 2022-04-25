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
        LibrarianTest(mainLibrary,librarianList);
        LibraryTest(mainLibrary,librarianList);
        PatronTest(mainLibrary,librarianList);
    }

    void LibrarianTest(Library mainLibrary, List<Librarian> librarianList) {
        HumanLibrarian librarian1 = new HumanLibrarian(mainLibrary, "Willson Tybur", "123");
        assertEquals("Willson Tybur", librarian1.getName());
        assertEquals(0, librarian1.getId());

        HumanLibrarian librarian2 = new HumanLibrarian(mainLibrary, "Kenny S", "321");
        assertEquals("Kenny S", librarian2.getName());
        assertEquals(1, librarian2.getId());

        HumanLibrarian librarian3 = new HumanLibrarian(mainLibrary, "S1mple", "09890");
        assertEquals("S1mple", librarian3.getName());
        assertEquals(2, librarian3.getId());

        Kiosk kiosk1 = new Kiosk(mainLibrary);
        assertEquals(3, kiosk1.getId());

        Kiosk kiosk2 = new Kiosk(mainLibrary);
        assertEquals(4, kiosk2.getId());

        Kiosk kiosk3 = new Kiosk(mainLibrary);
        assertEquals(5, kiosk3.getId());


        librarianList.add(librarian1);
        librarianList.add(librarian2);
        librarianList.add(librarian3);
        librarianList.add(kiosk1);
        librarianList.add(kiosk2);
        librarianList.add(kiosk3);
        assertEquals(6, librarianList.size());

        librarian1.addPatron("Vattana", "123");
        librarian1.addPatron("Jackson", "321");
        librarian1.addPatron("VZ", "0909");
        librarian1.addPatron("Yelena", "007");
        librarian1.addPatron("Katy", "555");

        assertEquals(6, librarianList.size());
    }

  
    void LibraryTest(Library mainLibrary, List<Librarian> librarianList) {
        for (int i = 0; i < librarianList.size(); i++) {
            mainLibrary.addLibrarian(librarianList.get(i)); 
        }

        assertEquals(6, librarianList.size());
        assertEquals(6, mainLibrary.getLibrarians().size());

        for (int j = 0; j < mainLibrary.getLibrarians().size(); j++) {
            assertEquals(librarianList.get(j), mainLibrary.getLibrarians().get(j));
        }
    }

    void PatronTest(Library mainLibrary, List<Librarian> librarianList) {
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
