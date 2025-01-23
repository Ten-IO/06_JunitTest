package lab06;

public class Book {
    String title, author, publisher;
    float price;
    int isbn, publishedYear;

    Book(String title, String author, float price, int isbn, String publisher, int publishedYear) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
    }

    public Book clone() {
        return new Book(title, author, price, isbn, publisher, publishedYear);
    }

    void show() {
        System.out.printf(" %-20s %-10s $%7.2f %14d %4d", title, author, price, isbn, publishedYear);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
}
