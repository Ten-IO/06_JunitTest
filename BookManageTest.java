package lab06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

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
    public void testAddBook() {
        String input = "Test title\nTest Author\n10.99\n111\nTest Publisher\n2023\n";
        simulateInput(input);
        Book prototype = new Book("Dummy Book", "Unknown", 1, 2, "Vila Corp.", 2024);
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
    void testUpdate() {
        bookManage.listBook.add(new Book("OriginTitle", "OriginAuthor", 10.0f, 111, "OriginPusblisher", 2022));
        String testInput = "1\nUpdateTitle\nUpdateAuthor\n20.0\n221\nUpdatedCorp.\n2023\n";
        simulateInput(testInput);
        bookManage.updateBook();

        Book updatedTest = bookManage.listBook.get(0);
        assertEquals("UpdateTitle", updatedTest.getTitle());
        assertEquals("UpdateAuthor", updatedTest.getAuthor());
        assertEquals(20.0, updatedTest.getPrice());
        assertEquals(221, updatedTest.getIsbn());
        assertEquals("UpdatedCorp.", updatedTest.getPublisher());
        assertEquals(2023, updatedTest.getPublishedYear());
    }

    @Test
    void testRemoveAllBooks() {
        bookManage.listBook.add(new Book("Book1", "Author1", 10.0f, 111, "AlphaPublisher", 2022));
        bookManage.listBook.add(new Book("Book2", "Author2", 15.5f, 111, "BetaPublisher", 2023));
        simulateInput("2\n");

        bookManage.removeBook();
        assertEquals(1, bookManage.listBook.size());
        assertEquals("Book1", bookManage.listBook.get(0).getTitle());
    }

    @Test
    void testRemoveAll() {
        bookManage.listBook.add(new Book("Book1", "Author1", 10.0f, 111, "AlphaPublisher", 2022));
        bookManage.listBook.add(new Book("Book2", "Author2", 15.5f, 111, "BetaPublisher", 2023));
        bookManage.removeAllBook();
        assertEquals(0, bookManage.listBook.size());
    }
}
