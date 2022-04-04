package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.util.List;

public class HumanLibrarian extends Librarian {
    private static String name;
    private static String password;
    
    public HumanLibrarian(Library library, String name, String password) {
        super(library);
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
        Patron newPatron = new Patron(library, name, password);
        library.addPatron(newPatron);
    }

    /***
     * @param name of the patron to be removed
     * @post the patron is removed from the library's list of patrons
     */
    public void removePatron(int id) {
        List<Patron> patrons = library.getPatrons();
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == id) {
                library.removePatron(patrons.get(i));
                return;
            }
        }
    }


    /***
     * @param name of the patron to be reported
     * @post the patron is added to the reported patron list
     */
    public void reportPatron(int id) {
        List<Patron> patrons = library.getPatrons();
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == id) {
                library.addReportedPatron(patrons.get(i));
                return;
            }
        }
    }


    /***
     * @param id of the patron to unreport
     * @post the patron is removed from the reported patron list
     */
    public void unreportPatron(int id) {
        
    }

    public String getName() {
        return name;
    }
}
