package edu.ithaca.dturnbull.LibraryManagementSystem;

public class Kiosk extends Librarian{

    public Kiosk(Library library) {
        super(library);
        this.id = library.getNextLibrarianId();
        library.increaseNextLibrarianId();
        this.library = library;
    }
}
