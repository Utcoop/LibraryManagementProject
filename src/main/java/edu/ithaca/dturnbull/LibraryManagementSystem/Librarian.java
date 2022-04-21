package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    protected int id;
    protected Library library;

    public Librarian(Library library) {
        this.library = library;
    }

    /***
     * 
     * @param accountId of the patron
     * @param password of the patron
     * @post the credential of the patron is confirmed
     */
    public boolean confirmCred(int accountId, String password) {
        List<Patron> patrons = new ArrayList<>(library.getPatrons());
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == accountId) {
                if (patrons.get(i).getPassword() == password) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * 
     */
    public void checkBalance(String patronId) {

    }

    public void payFine(double amount) {

    }

    /***
     * @param title of the book to check if available
     */
    public Boolean checkBook(String title) {
        List<Book> books = library.getBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title == title) {
                if (books.get(i).copies > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * @param title of the book to borrw, id of the patron borrowing
     * @post the number of the book copies is decremented by 1 if the book is available
     * @throws IllegalArgumentException if the book cannot be borrowed due to the lack of copies available
     */
    public void borrowBook(String title, int patronId) {
        List<Book> books = library.getBooks();
        List<Patron> patrons = library.getPatrons();
        if (checkBook(title)) {
        for (int j = 0; j < patrons.size(); j++) {
            if (patrons.get(j).getId() == patronId) {
                if (patrons.get(j).booksOut.size() > patrons.get(j).maxBooks) {
                    throw new IllegalArgumentException();
                } else {
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).title == title) {
                            patrons.get(j).booksOut.add(new Book(books.get(i)));
                            books.get(i).copies--;
                        }
                    }
                }
            }
        }       
       } else {
           throw new IllegalArgumentException("Book is not available to be borrowed.");
       }
    }

    /***
     * @param title of the book, id of the patron returning
     * @post the number of the book copies is incremented by 1
     */
    public void returnBook(String title, int patronId) {
        List<Book> books = library.getBooks();
        List<Patron> patrons = library.getPatrons();

        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == patronId) {
                List<Book> patronBookOutList = patrons.get(i).booksOut;
                for (int j = 0; j < patronBookOutList.size(); j++) {
                    if (patronBookOutList.get(j).title == title) {
                        patronBookOutList.remove(patronBookOutList.get(j));
                        for (int n = 0; n < books.size(); n++) {
                            if (books.get(n).title == title) {
                                books.get(n).copies++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void addToWishList(String title) {

    }

    public void removeFromWishList(String title) {

    }

    public void calculateFine() {

    }

    public void checkWishList() {

    }

    public int getId() {
        return id;
    }
}
