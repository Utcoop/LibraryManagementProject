package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;

import edu.ithaca.dturnbull.LibraryManagementSystem.HumanLibrarian;
import edu.ithaca.dturnbull.LibraryManagementSystem.Kiosk;
import edu.ithaca.dturnbull.LibraryManagementSystem.Library;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {
    Library ith = new Library();
    
    @Test
    void humanLibrarianConstructorTest() { 
        HumanLibrarian librarian1 = new HumanLibrarian(ith, "Casey Canberg", "123");
        assertEquals("Casey Canberg", librarian1.getName());
        assertEquals(0, librarian1.getId());

        HumanLibrarian librarian2 = new HumanLibrarian(ith, "Casandra Lopez", "123");
        assertEquals("Casandra Lopez", librarian2.getName());
        assertEquals(1, librarian2.getId());
    }

    @Test
    void kioskConstructorTest() {
        Kiosk kiosk1 = new Kiosk(ith);
        assertEquals(2, kiosk1.getId());

        Kiosk kiosk2 = new Kiosk(ith);
        assertEquals(3, kiosk2.getId());
    }

    @Test
    void addPatronTest() {
        HumanLibrarian librarian1 = new HumanLibrarian(ith, "Casey Canberg", "123");
        assertEquals(0, librarian1.getPatrons().size());

        librarian1.addPatron("Vattana", "123");
        assertEquals(1, librarian1.getPatrons().size());
        assertEquals("Vattana", librarian1.getPatrons().get(0).getName());

        librarian1.addPatron("Jackson", "123");
        assertEquals(2, librarian1.getPatrons().size());
        assertEquals("Jackson", librarian1.getPatrons().get(1).getName());
    }

    @Test
    void removePatronTest() {
        HumanLibrarian librarian1 = new HumanLibrarian(ith, "Casey Canberg", "123");

        assertEquals(2, librarian1.getPatrons().size());
        librarian1.removePatron(1);

        assertEquals(1, librarian1.getPatrons().size());
        
        librarian1.removePatron(0);
        assertEquals(0, librarian1.getPatrons().size());
    }

    @Test
    void reportPatronTest() {
        HumanLibrarian librarian1 = new HumanLibrarian(ith, "Casey Canberg", "123");
        librarian1.addPatron("Vattana", "123");
        librarian1.addPatron("Jackson", "123");

        assertEquals(0,ith.getReportedPatrons().size());
        librarian1.reportPatron(3);

        assertEquals(1,ith.getReportedPatrons().size());

        librarian1.reportPatron(4);
        assertEquals(1,ith.getReportedPatrons().size());
    }

    @Test
    void confirmCredTest() {
        Kiosk kiosk2 = new Kiosk(ith);
    }
}
