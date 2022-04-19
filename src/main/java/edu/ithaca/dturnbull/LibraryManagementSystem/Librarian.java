package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    protected static int id;
    protected static Library library;

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
    public void checkBook(String title) {

    }

    /***
     * @param title of the book to borrw, id of the patron borrowing
     * @post the number of the book copies is decremented by 1 if the book is available
     */
    public void borrowBook(String title, int patronId) {

    }

    /***
     * @param title of the book, id of the patron returning
     * @post the number of the book copies is incremented by 1
     */
    public void returnBook(String title, int patronId) {

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
