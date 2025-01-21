package lab06;

import java.util.ArrayList;
import java.util.Scanner;

public class BookManage {
    Book prototype = new Book("Dummy Book", "Unknown", 1.0f, 002, "Vila Corp.", 2024);
    ArrayList<Book> listBook = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    void addMultipleBooks() {
        System.out.print("How many books do you want to create?\n>>");
        int count = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < count; i++) {
            System.out.println("Book " + (i + 1) + ":");
            addBook(prototype);
        }
    }

    void showOption() {
        while (true) {
            System.out.print("""
                    ------------------------- Menu -------------------------
                      1. List books
                      2. Add a new book (prototype)
                      3. Create multiple books (prototype)
                      4. Update a book by index
                      5. Remove a book by index
                      6. Remove all books
                      0. Exit
                    --------------------------------------------------------
                    >> Choose an option: """);
            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    listAll();
                    break;
                case 2:
                    addBook(prototype);
                    break;
                case 3:
                    addMultipleBooks();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    removeAllBook();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    void addBook(Book prototype) {
        Book newBook = prototype.clone();
        System.out.print("Enter title: ");
        newBook.setTitle(scan.nextLine());
        System.out.print("Enter author: ");
        newBook.setAuthor(scan.nextLine());
        System.out.print("Enter price: ");
        newBook.setPrice(scan.nextFloat());
        scan.nextLine();
        System.out.print("Enter ISBN: ");
        newBook.setIsbn(scan.nextInt());
        scan.nextLine();
        System.out.print("Enter publisher: ");
        newBook.setPublisher(scan.nextLine());
        System.out.print("Enter published year: ");
        newBook.setPublishedYear(scan.nextInt());
        scan.nextLine();
        listBook.add(newBook);
        if (newBook.title.isBlank())
            newBook.title = prototype.title;
        if (newBook.author.isBlank())
            newBook.author = prototype.author;
        if (newBook.publisher.isBlank())
            newBook.publisher = prototype.publisher;
        System.out.println("Book added successfully.");
    }

    void listAll() {
        for (Book i : listBook) {
            i.show();
        }
    }

    void updateBook() {
        System.out.print("Index to update: ");
        int index = scan.nextInt() - 1;
        scan.nextLine();
        if (index >= 0 && index < listBook.size()) {
            Book book = listBook.get(index);
            System.out.print("New Title: ");
            String newTitle = scan.nextLine();
            if (!newTitle.isBlank())
                book.setTitle(scan.nextLine());
            System.out.print("Author: ");
            String newAuthor = scan.nextLine();
            if (!newAuthor.isBlank()) {
                book.setAuthor(scan.nextLine());
            }
            System.out.print("Price: ");
            scan.nextLine();
            book.setPrice(scan.nextFloat());
            scan.nextLine();
            System.out.print("ISBN: ");
            book.setIsbn(scan.nextInt());
            scan.nextLine();
            String newPublisher = scan.nextLine();
            if (!newPublisher.isBlank()) {
                book.setPublisher(newPublisher);
            }
            System.out.print("Year of published: ");
            book.setPublishedYear(scan.nextInt());
        } else {
            System.out.println("Index does not exist");
        }
    }

    void removeBook() {
        System.out.print("Index to remove: ");
        int index = scan.nextInt() - 1;
        scan.nextLine();
        if (index >= 0 && index < listBook.size()) {
            listBook.remove(index);
            System.out.println("Book successfully removed");
        } else {
            System.out.println("Index does not exist");
        }
    }

    void removeAllBook() {
        listBook.clear();
        System.out.println("All books successfully removed");
    }

    public static void main(String[] args) {
        BookManage foo = new BookManage();
        foo.addBook(new Book("Dummy Book", "Unknown", 1.0f, 002, "Vila Corp.", 2024));
        foo.listAll();
    }
}
