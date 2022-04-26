package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.LinkedList;
import java.util.List;

public class Patron {
    int Id;
    String password;
    String name;
    Double fines;
    List<Book> booksOut;
    int maxBooks = 5;
    List<Book> wishlist;
    Library lib;

    public Patron(Library library, String name, String password) {
        lib = library;
        Id = lib.getNextPatronId();
        lib.increaseNextPatronId();
        this.password = password;
        this.name = name;
        fines = 0.0;
        booksOut = new LinkedList<>();
        wishlist = new LinkedList<>();
    }

    /**
     * 
     * @param book book to add to wishlist
     * @throws InvalidBookException
     * @return True if the book was added from wishlist successfully and false
     *         otherwise
     */
    public boolean addToWishlist(String bookT) throws InvalidBookException {
        List<Book> books = lib.getBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(bookT)) {
                if (!wishlist.contains(books.get(i))) {
                    wishlist.add(books.get(i));
                    return true;
                } else {
                    throw new InvalidBookException("This book is already in your wishlist");
                }
            }
        }
        throw new InvalidBookException("We do not have this book");
    }

    /**
     * @param book book to remove from wishlist
     * @throws InvalidBookException
     * @return True if the book was removed from wishlist successfully and false
     *         otherwise
     */
    public boolean removeFromWishlist(String bookT) throws InvalidBookException {
        for (int i = 0; i < wishlist.size(); i++) {
            if (wishlist.get(i).getTitle().equals(bookT)) {
                wishlist.remove(i);
                return true;
            }
        }
        throw new InvalidBookException("This book isn't in your wishlist");
    }

    /**
     * 
     * @param payment
     * @return
     */
    public double payFines(double payment) throws Exception {
        if (isNumberValid(payment)) {
            if (payment <= fines) {
                fines -= payment;
                fines = Math.round(fines * 100.0) / 100.0; // Multiply by 100 and round to cut off all decimals past the
                                                           // hundreths place. Divide by 100 to make sure the number has
                                                           // two decimasl again
                return fines;
            } else {
                throw new IllegalArgumentException("Payment is too large");
            }
        } else {
            throw new IllegalArgumentException("Payment amount is invalid.");
        }
    }

    /**
     * @return the fines that are outstanding in this account
     */
    public double checkFines() {
        return fines;
    }

    /**
     * @return a string that represents the current wishlist
     */
    public String checkWishlist() {
        String books = "";
        for (int i = 0; i < wishlist.size(); i++) {
            books += wishlist.get(i).getTitle() + "\n";
        }
        return books;
    }

    /**
     * 
     * @param book
     * @return True if the book was checked out successfully and false otherwise
     */
    public Boolean bookCheckout(Book book) {
        // TODO
        return false;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @post checks to see if the @param num is valid
     */
    public static boolean isNumberValid(double num) {
        if (num < 0) {
            return false;
        }
        String numString = Double.toString(num); // convert number to string
        int decimalIndex = numString.indexOf("."); // Find index of ".""
        int decimalPlaces = numString.length() - decimalIndex - 1; // Subtract total length by the index of ".".
                                                                   // Subtract by an extra 1 to account for index 0
        return (decimalPlaces <= 2); // Check to see if decimal places is less than 2
    }
}