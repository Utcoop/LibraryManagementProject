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
        throw new InvalidBookException("This book doesn't exist in the library");
    }

    /**
     * @return the fines that are outstanding in this account
     */
    public double checkFines() {
        // TODO
        return -1;
    }

    /**
     * @return a string that represents the current wishlist
     */
    public String checkWishlist() {
        // TODO
        return "";
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
}