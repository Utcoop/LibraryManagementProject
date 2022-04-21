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

    // String title, String author, String publicationDate, String genre, double
    // cost
    /***
     * @param title, author, publicationDate, genre, cost of the new book
     * @post the new book is added to the library's book list
     * @throws UnrecognizedException
     */
    public void addBook(String title, String author, String publicationDate, String genre, double cost)
            throws UnrecognizedException {
        Book newBook = new Book(title, author, publicationDate, genre, cost);
        books.add(newBook);
    }

    /***
     * @param title of the book to be removed
     * @post the book is removed from the library's book list
     */
    public void removeBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                books.remove(book);
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

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public void removeLibrarian(Librarian librarian) {
        librarians.remove(librarian);
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

    /**
     * 
     * @param id       user attempts to login in with this id
     * @param password user attempts to login with this password
     * @return true if the combination is true and false otherwise
     * @throws InvalidUserException
     */
    public Boolean PatronLogin(int Id, String pwrd) throws InvalidUserException {
        int l = 0, r = patrons.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // Check if x is present at mid
            if (patrons.get(m).getId() == Id){
                if (patrons.get(m).getPassword().equals(pwrd)) {
                    return true;
                } else {
                    throw new InvalidUserException("Invalid ID/Password combination.");
                }
            }
            // If x greater, ignore left half
            if (patrons.get(m).getId() < Id)
                l = m + 1;
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
        // if we reach here, then element was
        // not present
        throw new InvalidUserException("Invalid ID/Password combination.");
    }

    /**
     * 
     * @param id       user attempts to login in with this id
     * @param password user attempts to login with this password
     * @return true if the combination is true and false otherwise
     * @throws InvalidUserException
     */
    public Boolean LibrarianLogin(int Id, String pwrd) throws InvalidUserException {
        int l = 0, r = librarians.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // Check if x is present at mid
            if (librarians.get(m).getId() == Id){
                if (librarians.get(m).getPassword().equals(pwrd)) {
                    return true;
                } else {
                    throw new InvalidUserException("Invalid ID/Password combination.");
                }
            }
            // If x greater, ignore left half
            if (librarians.get(m).getId() < Id)
                l = m + 1;
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
        // if we reach here, then element was
        // not present
        throw new InvalidUserException("Invalid ID/Password combination.");
    }
}
