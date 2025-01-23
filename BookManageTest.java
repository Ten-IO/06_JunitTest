package lab06;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookManageTest {
    BookManage bookManage;

    void simulateInput(String input) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);
        bookManage.scan = new Scanner(System.in);
    }

    @BeforeEach
    void setup() {
        bookManage = new BookManage();
    }

    @Test
    void testOption() {
        simulateInput(
                "10\n" + "1\n" + "2\n\n\n1\n1\n\n-10\n" + "4\n20\n" + "5\n1\n " + "3\n1\n\n\n1\n1\n\n101\n" + "6\n0\n");
        bookManage.showOption();
        assertEquals(0, bookManage.listBook.size());
    }

    @Test
    public void testAddBook() {
        Book prototype = new Book("Dummy Book", "Unknown", 1, 2, "Vila Corp.", 2024);
        String input = "Test title\nTest Author\n10.99\n111\nTest Publisher\n2023\n";
        simulateInput(input);
        bookManage.addBook(prototype);

        assertEquals(1, bookManage.listBook.size());
        Book addedTest = bookManage.listBook.get(0);
        assertEquals("Test title", addedTest.title);
        assertEquals("Test Author", addedTest.author);
        assertEquals(10.99, addedTest.price, 1e-2);
        assertEquals(111, addedTest.isbn);
        assertEquals("Test Publisher", addedTest.publisher);
        assertEquals(2023, addedTest.publishedYear);
    }

    @Test
    public void TestBlankAddDefaultFillBook() {
        Book prototype = new Book("Prototype Book", "Unknown", 1, 2, "Vila Corp.", 2024);

        String input = "\n\n10.99\n111\n\n2023\n";
        simulateInput(input);
        bookManage.addBook(prototype);
        Book addedTest = bookManage.listBook.get(0);
        assertEquals(addedTest.title, "Prototype Book");
        assertEquals(addedTest.author, "Unknown");
        assertEquals(addedTest.publisher, "Vila Corp.");
    }

    @Test
    public void testAddMultipleBook() {
        String input = "3\n" +
                "Java for Dummies\nBarry Burd\n20.9\n978\nWiley Corp.\n2022\n" +
                "Test title\nTest Author\n10.99\n111\nTest Publisher\n2023\n" +
                "dummy book\nUnknown\n0\n111\nVila Corp.\n2024\n";

        simulateInput(input);

        bookManage.addMultipleBooks();

        assertEquals(3, bookManage.listBook.size());

        Book book1 = bookManage.listBook.get(0);
        assertEquals("Java for Dummies", book1.getTitle());
        assertEquals("Barry Burd", book1.getAuthor());
        assertEquals(20.9, book1.getPrice(), 1e-2);
        assertEquals(978, book1.getIsbn());
        assertEquals("Wiley Corp.", book1.getPublisher());
        assertEquals(2022, book1.getPublishedYear());

        Book book2 = bookManage.listBook.get(1);
        assertEquals("Test title", book2.getTitle());
        assertEquals("Test Author", book2.getAuthor());
        assertEquals(10.99, book2.getPrice(), 1e-2);
        assertEquals(111, book2.getIsbn());
        assertEquals("Test Publisher", book2.getPublisher());
        assertEquals(2023, book2.getPublishedYear());

        Book book3 = bookManage.listBook.get(2);
        assertEquals("dummy book", book3.getTitle());
        assertEquals("Unknown", book3.getAuthor());
        assertEquals(0, book3.getPrice(), 1e-2);
        assertEquals(111, book3.getIsbn());
        assertEquals("Vila Corp.", book3.getPublisher());
        assertEquals(2024, book3.getPublishedYear());
    }

    @Test
    public void testFullChangedUpdate() {
        bookManage.listBook.add(new Book("OriginTitle", "OriginAuthor", 10.0f, 111, "OriginPublisher", 2022));
        String testInput = "1\nUpdateTitle\nUpdateAuthor\n20.0\n221\nUpdatedCorp.\n2023\n";
        simulateInput(testInput);
        bookManage.updateBook();

        Book updatedTest = bookManage.listBook.get(0);
        assertEquals("UpdateTitle", updatedTest.getTitle());
        assertEquals("UpdateAuthor", updatedTest.getAuthor());
        assertEquals(20.0, updatedTest.getPrice(), 0.01);
        assertEquals(221, updatedTest.getIsbn());
        assertEquals("UpdatedCorp.", updatedTest.getPublisher());
        assertEquals(2023, updatedTest.getPublishedYear());
    }

    @Test
    public void testIgnoreStringInputUpdate() {
        bookManage.listBook.add(new Book("OriginTitle", "OriginAuthor", 10.0f, 111, "OriginPublisher", 2022));
        String testInput = "1\n\n\n20.0\n221\n\n2023\n";
        simulateInput(testInput);
        bookManage.updateBook();

        Book updatedTest = bookManage.listBook.get(0);
        assertEquals("OriginTitle", updatedTest.getTitle());
        assertEquals("OriginAuthor", updatedTest.getAuthor());
        assertEquals(20.0, updatedTest.getPrice(), 0.01);
        assertEquals(221, updatedTest.getIsbn());
        assertEquals("OriginPublisher", updatedTest.getPublisher());
        assertEquals(2023, updatedTest.getPublishedYear());
    }

    @Test
    public void testIgnoreOutboundInputUpdate() {
        bookManage.listBook.add(new Book("OriginTitle", "OriginAuthor", 10.0f, 1, "OriginPublisher", 2022));
        String testInput = "41\n";
        simulateInput(testInput);
        bookManage.updateBook();

        Book updatedTest = bookManage.listBook.get(0);
        assertEquals("OriginTitle", updatedTest.getTitle());
        assertEquals("OriginAuthor", updatedTest.getAuthor());
        assertEquals(10.0, updatedTest.getPrice(), 0.01);
        assertEquals(01, updatedTest.getIsbn());
        assertEquals("OriginPublisher", updatedTest.getPublisher());
        assertEquals(2022, updatedTest.getPublishedYear());
    }

    @Test
    public void testRemoveBookThatExist() {
        bookManage.listBook.add(new Book("Book1", "Author1", 10.0f, 111, "AlphaPublisher", 2022));
        bookManage.listBook.add(new Book("Book2", "Author2", 15.5f, 111, "BetaPublisher", 2023));
        simulateInput("2\n");

        bookManage.removeBook();
        assertEquals(1, bookManage.listBook.size());
        assertEquals("Book1", bookManage.listBook.get(0).getTitle());
    }

    @Test
    public void testRemoveBookThatDoesNotExist() {
        bookManage.listBook.add(new Book("Book1", "Author1", 10.0f, 111, "AlphaPublisher", 2022));
        bookManage.listBook.add(new Book("Book2", "Author2", 15.5f, 111, "BetaPublisher", 2023));
        simulateInput("0\n");
        bookManage.removeBook();
        assertEquals(2, bookManage.listBook.size());
    }

    @Test
    void testRemoveAll() {
        bookManage.listBook.add(new Book("Book1", "Author1", 10.0f, 111, "AlphaPublisher", 2022));
        bookManage.listBook.add(new Book("Book2", "Author2", 15.5f, 111, "BetaPublisher", 2023));
        bookManage.removeAllBook();
        assertEquals(0, bookManage.listBook.size());
    }
}
