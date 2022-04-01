package edu.ithaca.dturnbull.LibraryManagementSystem;

public class InsufficientFundsException extends Exception{
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String s) {
        super(s);
    }

}