package edu.ithaca.dturnbull.LibraryManagementSystem;

import org.junit.jupiter.api.Test;

import edu.ithaca.dturnbull.LibraryManagementSystem.HumanLibrarian;
import edu.ithaca.dturnbull.LibraryManagementSystem.Kiosk;
import edu.ithaca.dturnbull.LibraryManagementSystem.Library;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        reportPatronTest(ith);
        unreportPatronTest(ith);
    }

    void reportPatronTest(Library lib) {
        Library ith = lib;
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

    void unreportPatronTest(Library lib) {
        Library ith = lib;
        HumanLibrarian librarian1 = new HumanLibrarian(ith, "Casey Canberg", "123");
        assertEquals(2,ith.getReportedPatrons().size());

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

    @Test
    void checkBookborrowBookreturnBookTest() throws UnrecognizedException, ParseException {
        Library library1 = new Library();

        HumanLibrarian librarian1 = new HumanLibrarian(library1, "S1mple", "123");
        library1.addLibrarian(librarian1);

        Kiosk kiosk1 = new Kiosk(library1);
        library1.addLibrarian(kiosk1);

        assertThrows(UnrecognizedException.class, ()-> library1.addBook( "Jumanji", "Lord","123213213","24-01-2015", "Horror", 1234.333, 0));

        library1.addBook("Percy Jackson", "Ali Bibi","123213213", "12-28-2002", "Adventure", 17.99, 5);
        library1.addBook("Harry Potter", "Jergie Paulo","123213213", "12-28-2002", "Science fiction", 17.99, 1);

        checkBookTest(library1, librarian1);
        checkBookTest(library1, kiosk1);

        librarian1.addPatron("Vattana", "123");
        librarian1.addPatron("Jackson", "123");

        borrowBookTest(library1, librarian1);
        returnBookTest(library1, librarian1);

        //calculateFineTest(library1, librarian1);
        //calculateFineTest(library1, kiosk1);
        //payFineTest(library1, librarian1);

    }


    void checkBookTest(Library library, Librarian librarian) {
        assertFalse(librarian.checkBook("Devil from the mars"));
        assertFalse(librarian.checkBook("Olivia and her journey"));

        assertTrue(librarian.checkBook("Percy Jackson"));
        assertTrue(librarian.checkBook("Harry Potter"));
    }

    void borrowBookTest(Library library, Librarian librarian) {
        librarian.borrowBook("Percy Jackson", 0);
        assertEquals(4, library.getBooks().get(0).copies);

        librarian.borrowBook("Percy Jackson", 1);
        assertEquals(3, library.getBooks().get(0).copies);

        assertThrows(IllegalArgumentException.class, () -> librarian.borrowBook("Asad", 1)); //no such book in the library to be borrowed
        
        librarian.borrowBook("Harry Potter", 1);
        assertEquals(0, library.getBooks().get(1).copies);

        assertThrows(IllegalArgumentException.class, () -> librarian.borrowBook("Harry Potter", 0)); //no more copies to be borrowed
    } 

    void returnBookTest(Library library, Librarian librarian) {
        assertEquals(1, library.getPatrons().get(0).booksOut.size());

        assertEquals(3, library.getBooks().get(0).copies);

        librarian.returnBook("Percy Jackson", 0);
        assertEquals(4, library.getBooks().get(0).copies);
        assertEquals(0, library.getPatrons().get(0).booksOut.size());

        assertEquals(2, library.getPatrons().get(1).booksOut.size());
        librarian.returnBook("Percy Jackson", 1);
        assertEquals(5, library.getBooks().get(0).copies);

        assertEquals(1, library.getPatrons().get(1).booksOut.size());
        assertEquals(0, library.getBooks().get(1).copies);

        librarian.returnBook("Harry Potter", 1);
        assertEquals(1, library.getBooks().get(1).copies);
        assertEquals(0, library.getPatrons().get(1).booksOut.size());
    }

    @Test
    void calculateFineTest() throws ParseException, UnrecognizedException {  
        Library library1 = new Library();

        HumanLibrarian librarian1 = new HumanLibrarian(library1, "S1mple", "123");
        library1.addLibrarian(librarian1);

        Kiosk kiosk1 = new Kiosk(library1);
        library1.addLibrarian(kiosk1);

        assertThrows(UnrecognizedException.class, ()-> library1.addBook( "Jumanji", "Lord","123213213","24-01-2015", "Horror", 1234.333, 0));

        library1.addBook("Percy Jackson", "Ali Bibi","123213213", "12-28-2002", "Adventure", 17.99, 5);
        library1.addBook("Harry Potter", "Jergie Paulo","123213213", "12-28-2002", "Science fiction", 17.99, 1);

        librarian1.addPatron("Vattana", "123");
        librarian1.addPatron("Jackson", "123");

        noLateTest(library1, librarian1);
        lateLessThan7Test(library1, librarian1);
        lateMoreThan7Test(library1, librarian1);

        payFineTest(library1, librarian1);
    }

    void noLateTest(Library library, Librarian librarian) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -7);
        String checkOutDate = formatter.format(calendar.getTime());
        calendar.add(Calendar.DATE, 7);
		String dueDate = formatter.format(calendar.getTime());

        librarian.borrowBook("Percy Jackson", 0);
        library.getPatrons().get(0).booksOut.get(0).checkOutDate = checkOutDate;
        library.getPatrons().get(0).booksOut.get(0).dueDate = dueDate;
        assertEquals(dueDate, library.getPatrons().get(0).booksOut.get(0).dueDate);

        library.getPatrons().get(0).booksOut.get(0).checkOutDate = checkOutDate;
        library.getPatrons().get(0).booksOut.get(0).dueDate = dueDate;
        librarian.calculateFine(library.getPatrons().get(0).getId());
        assertEquals(0, library.getPatrons().get(0).fines); //no fine is applied if book is returned on time
    }

    void lateLessThan7Test(Library library, Librarian librarian) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DATE, -9);
        String checkOutDate = formatter.format(calendar1.getTime());
        calendar1.add(Calendar.DATE, 7);
		String dueDate = formatter.format(calendar1.getTime());

        library.getPatrons().get(0).booksOut.get(0).checkOutDate = checkOutDate;
        library.getPatrons().get(0).booksOut.get(0).dueDate = dueDate;
        assertEquals(dueDate, library.getPatrons().get(0).booksOut.get(0).dueDate);

        library.getPatrons().get(0).booksOut.get(0).checkOutDate = checkOutDate;
        library.getPatrons().get(0).booksOut.get(0).dueDate = dueDate;
        librarian.calculateFine(library.getPatrons().get(0).getId());
        assertEquals(5, library.getPatrons().get(0).fines); //the fine is 5 dollars within the late days is less than 7 days

    }

    void lateMoreThan7Test(Library library, Librarian librarian) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, -15);
        String checkOutDate = formatter.format(calendar2.getTime());
        calendar2.add(Calendar.DATE, 7);
		String dueDate = formatter.format(calendar2.getTime());

        library.getPatrons().get(0).booksOut.get(0).checkOutDate = checkOutDate;
        library.getPatrons().get(0).booksOut.get(0).dueDate = dueDate;
        assertEquals(dueDate, library.getPatrons().get(0).booksOut.get(0).dueDate);

        library.getPatrons().get(0).booksOut.get(0).checkOutDate = checkOutDate;
        library.getPatrons().get(0).booksOut.get(0).dueDate = dueDate;
        librarian.calculateFine(library.getPatrons().get(0).getId());
        librarian.calculateFine(library.getPatrons().get(0).getId());
        assertEquals(library.getPatrons().get(0).booksOut.get(0).cost + 5.0, library.getPatrons().get(0).fines); //the fine is the cost of the book after 7 days late
    }

    void payFineTest(Library library, HumanLibrarian librarian) {
        assertEquals(22.99, library.getPatrons().get(0).fines);

        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), -1)); //no negative amount
        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), -5)); 
        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), 1.555)); //no decimal places more than 2
        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), 100.1340)); //no decimal places more than 2

        librarian.payFine(library.getPatrons().get(0).getId(), 5.00);
        assertEquals(17.99, library.getPatrons().get(0).fines);
        
        librarian.payFine(library.getPatrons().get(0).getId(), 10.00);
        assertEquals(7.99, library.getPatrons().get(0).fines); 
        librarian.payFine(library.getPatrons().get(0).getId(), 9.99);
        assertEquals(-2.0, library.getPatrons().get(0).fines);
      
    }
}
