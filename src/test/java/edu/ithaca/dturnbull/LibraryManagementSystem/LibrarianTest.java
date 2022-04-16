package edu.ithaca.dturnbull.LibraryManagementSystem;

import org.junit.jupiter.api.Test;

import edu.ithaca.dturnbull.LibraryManagementSystem.HumanLibrarian;
import edu.ithaca.dturnbull.LibraryManagementSystem.Kiosk;
import edu.ithaca.dturnbull.LibraryManagementSystem.Library;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {
    
    
    @Test
    void humanLibrarianAndKioskConstructorTest() {
        Library lib = new Library();
        humanLibrarianConstructorTest(lib);
        kioskConstructorTest(lib);
    }

    void humanLibrarianConstructorTest(Library lib) { 
        HumanLibrarian casey = new HumanLibrarian(lib, "Casey Canberg", "123");
        assertEquals("Casey Canberg", casey.getName());
        assertEquals(0, casey.getId());

        HumanLibrarian casandra = new HumanLibrarian(lib, "Casandra Lopez", "123");
        assertEquals("Casandra Lopez", casandra.getName());
        assertEquals(1, casandra.getId());
    }

    void kioskConstructorTest(Library lib) {
        Kiosk kiosk1 = new Kiosk(lib);
        assertEquals(2, kiosk1.getId());

        Kiosk kiosk2 = new Kiosk(lib);
        assertEquals(3, kiosk2.getId());

        Kiosk kiosk3 = new Kiosk(lib);
        assertEquals(4, kiosk3.getId());
    }

    @Test
    void addPatronTest() {
        Library ith = new Library();
        HumanLibrarian librarian1 = new HumanLibrarian(ith, "Casey Canberg", "123");
        assertEquals(0, ith.getPatrons().size());

        librarian1.addPatron("Vattana", "123");
        assertEquals(1, ith.getPatrons().size());
        assertEquals("Vattana", ith.getPatrons().get(0).getName());

        librarian1.addPatron("Jackson", "123");
        assertEquals(2, ith.getPatrons().size());
        assertEquals("Jackson", ith.getPatrons().get(1).getName());
    }

    @Test
    void removePatronTest() {
        Library library = new Library();
        HumanLibrarian librarian1 = new HumanLibrarian(library, "Casey Canberg", "123");

        assertEquals(0, library.getPatrons().size());

        librarian1.addPatron("Vattana", "123");
        librarian1.addPatron("Jackson", "123");

        librarian1.removePatron(library.getPatrons().get(0).getId());

        assertEquals(1, library.getPatrons().size());
        
        librarian1.removePatron(library.getPatrons().get(0).getId());
        assertEquals(0, library.getPatrons().size());
    }

    @Test
    void reportPatronAndUnreportTest() {
        Library ith = new Library();
        HumanLibrarian librarian1 = new HumanLibrarian(ith, "Casey Canberg", "123");
        librarian1.addPatron("Vattana", "123");
        librarian1.addPatron("Jackson", "123");

        assertEquals(0,ith.getReportedPatrons().size());
        librarian1.reportPatron(ith.getPatrons().get(0).getId());

        assertEquals(1,ith.getReportedPatrons().size());
        assertEquals("Vattana", ith.getReportedPatrons().get(0).getName());

        librarian1.reportPatron(ith.getPatrons().get(1).getId());
        assertEquals(2,ith.getReportedPatrons().size());
        assertEquals("Jackson", ith.getReportedPatrons().get(1).getName());

        librarian1.unreportPatron(ith.getReportedPatrons().get(0).getId());
        assertEquals(1,ith.getReportedPatrons().size());

        librarian1.unreportPatron(ith.getReportedPatrons().get(0).getId());
        assertEquals(0,ith.getReportedPatrons().size());
    }

    @Test
    void confirmCredTest() {
        Library ith = new Library();
        HumanLibrarian kate = new HumanLibrarian(ith, "Kate Kerry", "123");
        kate.addPatron("VZ", "54321");

        Kiosk kiosk2 = new Kiosk(ith);

        assertEquals(false, kiosk2.confirmCred(ith.getPatrons().get(0).getId(), "12345"));
        assertEquals(true, kiosk2.confirmCred(ith.getPatrons().get(0).getId(), "54321"));

        kate.addPatron("Jackson", "rickroll");
        
        assertEquals(false, kiosk2.confirmCred(ith.getPatrons().get(1).getId(), "salami"));
        assertEquals(true, kiosk2.confirmCred(ith.getPatrons().get(1).getId(), "rickroll"));
    }
}
