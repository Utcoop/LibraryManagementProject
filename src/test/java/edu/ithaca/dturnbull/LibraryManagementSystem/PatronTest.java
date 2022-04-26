package edu.ithaca.dturnbull.LibraryManagementSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    void wishlistAddTest() throws Exception {
        Library lib = new Library();
        Patron jackson = new Patron(lib, "Jackson", "password");
        lib.addBook("Percy Jackson", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 2", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 3", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 4", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 5", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Coding for Dumbies", "Some Guy", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);

        // Ensures that all the example books can be added to a wishlist successfully
        assertTrue(jackson.addToWishlist("Percy Jackson"));
        assertEquals("Percy Jackson\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 2"));
        assertEquals("Percy Jackson\nPercy Jackson 2\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 3"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 4"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 5"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\n",jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Coding for Dumbies"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\nCoding for Dumbies\n",jackson.checkWishlist());

        // Tests to make sure exceptions are thrown if the book title is not found in
        // the library.
        assertThrows(InvalidBookException.class, () -> jackson.addToWishlist("Perry Jackson"));
        assertThrows(InvalidBookException.class, () -> jackson.addToWishlist("ASDF"));
        // Tests to make sure the same book can't be added to the library at the same
        // time.
        assertThrows(InvalidBookException.class, () -> jackson.addToWishlist("Percy Jackson"));
    }

    @Test
    void wishlistRemoveTest() throws Exception {
        Library lib = new Library();
        Patron jackson = new Patron(lib, "Jackson", "password");
        lib.addBook("Percy Jackson", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 2", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 3", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 4", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Percy Jackson 5", "Rick Riordan", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);
        lib.addBook("Coding for Dumbies", "Some Guy", "1234567891012", "02-12-2000", "Adventure", 10.00, 1);

        // Ensures that all the example books can be added to a wishlist successfully
        assertTrue(jackson.addToWishlist("Percy Jackson"));
        assertEquals("Percy Jackson\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 2"));
        assertEquals("Percy Jackson\nPercy Jackson 2\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 3"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 4"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Percy Jackson 5"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\n", jackson.checkWishlist());
        assertTrue(jackson.addToWishlist("Coding for Dumbies"));
        assertEquals("Percy Jackson\nPercy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\nCoding for Dumbies\n", jackson.checkWishlist());

        //Exception is thrown when the book being removed is not in the wishlist
        assertThrows(InvalidBookException.class, () -> jackson.removeFromWishlist("Percy Jackson 6"));
        assertThrows(InvalidBookException.class, () -> jackson.removeFromWishlist("Coding for Smarties"));
        assertThrows(InvalidBookException.class, () -> jackson.removeFromWishlist("Perry Johnson"));
        
        //Remove from the start of the list
        assertTrue(jackson.removeFromWishlist("Percy Jackson"));
        assertEquals("Percy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\nCoding for Dumbies\n",jackson.checkWishlist());
        //Remove from the end of the list
        assertTrue(jackson.removeFromWishlist("Coding for Dumbies"));
        assertEquals("Percy Jackson 2\nPercy Jackson 3\nPercy Jackson 4\nPercy Jackson 5\n",jackson.checkWishlist());
        //Remove from the middle of the list
        assertTrue(jackson.removeFromWishlist("Percy Jackson 3"));
        assertEquals("Percy Jackson 2\nPercy Jackson 4\nPercy Jackson 5\n",jackson.checkWishlist());
        assertTrue(jackson.removeFromWishlist("Percy Jackson 4"));
        assertEquals("Percy Jackson 2\nPercy Jackson 5\n",jackson.checkWishlist());
        //Remove the final two books
        assertTrue(jackson.removeFromWishlist("Percy Jackson 5"));
        assertEquals("Percy Jackson 2\n",jackson.checkWishlist());
        assertTrue(jackson.removeFromWishlist("Percy Jackson 2"));
        assertEquals("",jackson.checkWishlist());

        //Throws an exception when attempting to remove a book from an empy list
        assertThrows(InvalidBookException.class, () -> jackson.removeFromWishlist("Percy Jackson"));
    }

    @Test
    void checkandpayFinesTest() throws ParseException, UnrecognizedException {  
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
        assertEquals(0, library.getPatrons().get(0).checkFines()); //no fine is applied if book is returned on time
    }

    void lateLessThan7Test(Library library, Librarian librarian) throws ParseException {
        //Date date = new Date();
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
        assertEquals(5, library.getPatrons().get(0).checkFines()); //the fine is 5 dollars within the late days is less than 7 days

    }

    void lateMoreThan7Test(Library library, Librarian librarian) throws ParseException {
        //Date date = new Date();
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
        assertEquals(library.getPatrons().get(0).booksOut.get(0).cost + 5.0, library.getPatrons().get(0).checkFines()); //the fine is the cost of the book after 7 days late
    }

    void payFineTest(Library library, HumanLibrarian librarian) {
        assertEquals(22.99, library.getPatrons().get(0).checkFines());

        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), -1)); //no negative amount
        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), -5)); 
        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), 1.555)); //no decimal places more than 2
        assertThrows(IllegalArgumentException.class, () -> librarian.payFine(library.getPatrons().get(0).getId(), 100.1340)); //no decimal places more than 2

        library.getPatrons().get(0).payFine(5.00, librarian);
        assertEquals(17.99, library.getPatrons().get(0).checkFines());
        
        library.getPatrons().get(0).payFine(10.00, librarian);
        assertEquals(7.99, library.getPatrons().get(0).fines); 
        library.getPatrons().get(0).payFine(9.99, librarian);
        assertEquals(-2.0, library.getPatrons().get(0).fines);
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
    }


    void checkBookTest(Library library, Librarian librarian) {
        assertFalse(librarian.checkBook("Devil from the mars"));
        assertFalse(librarian.checkBook("Olivia and her journey"));

        assertTrue(librarian.checkBook("Percy Jackson"));
        assertTrue(librarian.checkBook("Harry Potter"));
    }

    void borrowBookTest(Library library, Librarian librarian) {
        library.getPatrons().get(0).borrowBook("Percy Jackson", librarian);
        assertEquals(4, library.getBooks().get(0).copies);

        library.getPatrons().get(1).borrowBook("Percy Jackson", librarian);
        assertEquals(3, library.getBooks().get(0).copies);

        assertThrows(IllegalArgumentException.class, () -> librarian.borrowBook("Asad", 1)); //no such book in the library to be borrowed
        
        library.getPatrons().get(1).borrowBook("Harry Potter", librarian);
        assertEquals(0, library.getBooks().get(1).copies);

        assertThrows(IllegalArgumentException.class, () -> librarian.borrowBook("Harry Potter", 0)); //no more copies to be borrowed
    } 

    void returnBookTest(Library library, Librarian librarian) {
        assertEquals(1, library.getPatrons().get(0).booksOut.size());

        assertEquals(3, library.getBooks().get(0).copies);

        library.getPatrons().get(0).returnBook("Percy Jackson", librarian);
        assertEquals(4, library.getBooks().get(0).copies);
        assertEquals(0, library.getPatrons().get(0).booksOut.size());

        assertEquals(2, library.getPatrons().get(1).booksOut.size());
        library.getPatrons().get(1).returnBook("Percy Jackson", librarian);
        assertEquals(5, library.getBooks().get(0).copies);

        assertEquals(1, library.getPatrons().get(1).booksOut.size());
        assertEquals(0, library.getBooks().get(1).copies);

        library.getPatrons().get(1).returnBook("Harry Potter", librarian);
        assertEquals(1, library.getBooks().get(1).copies);
        assertEquals(0, library.getPatrons().get(1).booksOut.size());
    }
}

