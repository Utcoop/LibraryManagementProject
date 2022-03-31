package edu.ithaca.dturnbull.bank;

public class HumanLibrarian extends Librarian {
    private static String name;
    private static String password;
    
    public HumanLibrarian(Library library, String name, String password) {
        super();
        this.id = library.getNextLibrarianId();
        library.increaseNextLibrarianId();
        this.name = name;
        this.password = password;
        this.library = library;
    }

    /***
     * @param name of the new patron
     * @param password of the new patron
     * @post patron is added to the library's list of patrons
     */
    public void addPatron(String name, String password) {

    }

    /***
     * @param name of the patron to be removed
     * @post the patron is removed from the library's list of patrons
     */
    public void removePatron(String name) {

    }


    /***
     * @param name of the patron to be reported
     * @post the patron is added to the reported patron list
     */
    public void reportPatron(String name) {

    }
}
