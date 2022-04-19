package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Librarian> librarians;
    private List<Patron> patrons; 
    private List<Book> books;
    private List<Patron> reportedPatrons;
    private int nextLibrarianId = 0;
    private int nextPatronId = 0;
    public Library() {
        librarians = new ArrayList<>();
        patrons = new ArrayList<>();
        books = new ArrayList<>();
        reportedPatrons = new ArrayList<>();
    }

    //String title, String author, String publicationDate, String genre, double cost
    /***
     * @param title, author, publicationDate, genre, cost of the new book
     * @post the new book is added to the library's book list
     * @throws UnrecognizedException
     */
    public void addBook(String title, String author, String publicationDate, String genre, double cost) throws UnrecognizedException {
        Book newBook = new Book(title, author, publicationDate, genre, cost);
        books.add(newBook);
    } 

    /***
     * @param title of the book to be removed
     * @post the book is removed from the library's book list
     */
    public void removeBook(String title) {
        for (Book book : books) {
            if (book.getTitle() == title) {
                books.remove(book);
            }
        }
    }

    /**
     * @param librarian object
     * @post the librarian object is added to the library's librarian object
     */
    public void addLibrarian(Librarian librarianOrKiosk) {
        librarians.add(librarianOrKiosk);
    }

    /**
     * @param librarian id
     * @post the librarian object is removed to the library's librarian object
     */
    public void removeLibrarian(int librarianOrKioskId) {
        for (int i = 0; i < librarians.size(); i++) {
            if (librarians.get(i).getId() == librarianOrKioskId) {
                librarians.remove(librarians.get(i));
            }
        }
    }   

    public void increaseNextLibrarianId() {
        nextLibrarianId++;
    }

    public int getNextLibrarianId() {
        return nextLibrarianId;
    }

    public void increaseNextPatronId() {
        nextPatronId++;
    }

    public int getNextPatronId() {
        return nextPatronId;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public List<Patron> getReportedPatrons() {
        return reportedPatrons;
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void removePatron(Patron patron) {
        patrons.remove(patron);
    }

    public void addReportedPatron(Patron patron) {
        reportedPatrons.add(patron);
    }

    public void removeReportedPatron(Patron patron) {
        reportedPatrons.remove(patron);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public static void main(String[] args) {
        Library library = new Library();
    HumanLibrarian librarian1 = new HumanLibrarian(library, "Kenny", "123");

    library.addLibrarian(librarian1);
 

    Kiosk kiosk1 = new Kiosk(library);
    library.addLibrarian(kiosk1);

    Kiosk kiosk2 = new Kiosk(library);
    library.addLibrarian(kiosk2);
    
    System.out.println(library.getLibrarians().get(0).getId());
    System.out.println(library.getLibrarians().get(1).getId());
    System.out.println(library.getLibrarians().get(2).getId());
    }
}
