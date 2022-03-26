package edu.ithaca.dturnbull.bank;

public class UnrecognizedException extends Exception{
    private static final long serialVersionUID = 1L;

    public UnrecognizedException(String s) {
        super(s);
    }

}
