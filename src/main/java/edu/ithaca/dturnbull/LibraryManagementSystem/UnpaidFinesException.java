package edu.ithaca.dturnbull.LibraryManagementSystem;

public class UnpaidFinesException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnpaidFinesException(String s) {
        super(s);
    }

}
