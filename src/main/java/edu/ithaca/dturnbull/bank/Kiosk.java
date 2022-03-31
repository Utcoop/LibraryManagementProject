package edu.ithaca.dturnbull.bank;

public class Kiosk extends Librarian{

    public Kiosk(Library library) {
        super();
        this.id = library.getNextLibrarianId();
        library.increaseNextLibrarianId();
        this.library = library;
    }
}
