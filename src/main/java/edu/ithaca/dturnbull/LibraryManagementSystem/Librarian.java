package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Librarian {
    private static final Double Double = null;
    protected int id;
    protected String password;
    protected Library library;

    public Librarian(Library library) {
        this.library = library;
    }

    /***
     * 
     * @param accountId of the patron
     * @param password  of the patron
     * @post the credential of the patron is confirmed
     */
    public boolean confirmCred(int accountId, String password) {
        List<Patron> patrons = new ArrayList<>(library.getPatrons());
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == accountId) {
                if (patrons.get(i).getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     *  @param Patron returns the fines of the patron
     */
    public double checkFines(Patron patron) {
        return patron.checkFines();
    }

    /***
     * @param title of the book to check if available
     */
    public Boolean checkBook(String title) {
        List<Book> books = library.getBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equals(title)) {
                if (books.get(i).copies > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * @param title of the book to borrw, id of the patron borrowing
     * @post the number of the book copies is decremented by 1 if the book is
     *       available
     * @throws IllegalArgumentException if the book cannot be borrowed due to the
     *                                  lack of copies available
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
                            if (books.get(i).title.equals(title)) {
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
                    if (patronBookOutList.get(j).title.equals(title)) {
                        patronBookOutList.remove(patronBookOutList.get(j));
                        for (int n = 0; n < books.size(); n++) {
                            if (books.get(n).title.equals(title)) {
                                books.get(n).copies++;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean addToWishlist(String title, Patron patron) throws InvalidBookException {
        return patron.addToWishlist(title);
    }

    public boolean removeFromWishlist(String title, Patron patron) throws InvalidBookException {
        return patron.removeFromWishlist(title);
    }

    /***
     * @throws ParseException
     * @post the specified patron's fines are updated
     */
    public void calculateFine(int patronId) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(date);
        Date date1 = formatter.parse(today);
        double accumulatedFines = 0.0;

        List<Patron> patrons = library.getPatrons();
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == patronId) {
                List<Book> booksOut = patrons.get(i).booksOut;
                for (int j = 0; j < booksOut.size(); j++) {
                    Date date2 = formatter.parse(booksOut.get(j).dueDate);
                    long diff = date1.getTime() - date2.getTime();
                    int lateDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    if (lateDays <= 0) {
                        accumulatedFines += 0;
                    } else if (lateDays <= 7) {
                        accumulatedFines += 5;
                    } else if (booksOut.get(j).penalized == false && lateDays > 7) {
                        booksOut.get(j).penalized = true;
                        accumulatedFines += booksOut.get(j).cost;
                    } else {
                    }
                    patrons.get(i).fines += accumulatedFines;
                }
            }
        }
    }

    /**
     * @param amount
     * @post the specified patron's fine is paid
     * @throws IllegalArgumentException if the amount is negative or have more than two decimal places
     */
    public void payFine(int patronId, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("No Negative Amount");
        }

        
        String amountString = Double.toString(amount);
        String decimalPlaces = amountString.split("\\.")[1].toString();
        if (decimalPlaces.length() > 2) {
           // if (amountString.split("\\.")[1].toString().length() > 2) {
                throw new IllegalArgumentException("No more than two decimal places");
            //}
        }

        List<Patron> patrons = library.getPatrons();
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == patronId) {
                patrons.get(i).fines -= amount;
                DecimalFormat df = new DecimalFormat("#.##");      
                patrons.get(i).fines = Double.valueOf(df.format(patrons.get(i).fines));
                
            }
        }
    }

    public String checkWishlist(Patron patron) {
        return patron.checkWishlist();
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

}
