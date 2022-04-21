package edu.ithaca.dturnbull.LibraryManagementSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private String fileName;
    private List<Book> books;
    Scanner scanner;
    public Main(String fileName) {
        this.fileName = fileName;
        books = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(",");
                Book book = new Book(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                books.add(book);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        Main main = new Main("BookList.txt");
        main.printBooks();
        main.run();
    }

    /**
     * addBook()
     * Used to add book to txt file
     * if book found, increments number of copies
     * method type: accessor
     */
    void addBook() {
        System.out.print("Enter book name: ");
        String name = nextLine();
        for (Book book : books) {
            if (book.name.equals(name)) {
                book.copies++;
                save();
                return;
            }
        }
        System.out.print("Enter book author: ");
        String director = nextLine();
        System.out.print("Enter copies: ");
        int copies = Integer.parseInt(nextLine());
        Book book = new Book(name, director, copies);
        books.add(book);
        save();
    }
    /**
     * nextLine()
     * @return line 
     */

    String nextLine() {
        String line = "";
        while(line.isEmpty()) {
            line = scanner.nextLine();
        }
        return line;
    }

    /**
     * checkoutBook()
     * Used to checkout book from the txt file
     * if book found, decrements number of copies
     * method type: accessor
     */
    void checkoutBook() throws Exception {
        System.out.print("Enter movie title: ");
        String name = nextLine();
        for (Book book : books) {
            if (book.name.equals(name)) {
                if (book.copies > 0) {
                    book.copies--;
                    save();
                    return;
                } else {
                    throw new Exception("Not found");
                }
            }
        }
    }


    /**
     * save()
     * Used to save book to text file
     * method type:mutator
     */
    void save() {
        try {
            File file = new File(fileName);
            file.delete();
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file, false);
            for (Book book : books) {
                outputStream.write(book.toString().getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * run()
     * runs main.java 
     */

    public void run() {
        scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Add new Book");
            System.out.println("2. Checkout Book");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(nextLine());
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    try {
                        checkoutBook();
                    } catch (Exception e) {
                        System.out.println("Movie not found");
                    }
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    /**
     * printBooks()
     * prints books in list while entering options
     * Allows user to know about available books
     * method type: accessor
     */
    void printBooks() {
        for (Book book : books) {
            System.out.print(book.toString());
        }
    }

    /**
     * constructor
     */

    class Book {
        String name;
        String author;
        int copies;
        public Book(String name, String author, int copies) {
            this.name = name;
            this.author = author;
            this.copies = copies;
        }

        public Book(String line) {
            String [] tokens = line.split(",");
            this.name = tokens[0];
            this.author = tokens[1];
            this.copies = Integer.parseInt(tokens[2]);
        }

        @Override
        public String toString() {
            return name + "," + author + "," + copies + "\n";
        }
    }
}