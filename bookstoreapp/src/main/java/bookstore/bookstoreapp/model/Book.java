package bookstore.bookstoreapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Long id;

    @NotEmpty(message = "Book's title cannot be empty.")
    @Size(min = 1, max = 250, message = "Title must be between 1 and 250 characters.")
    private String title;   

    @NotEmpty(message = "Author cannot be empty.")
    private String author;

    @Min(value = 1000, message = "Publication year must be realistic.")
    @Max(value = 2100, message = "Publication year must be realistic.")
    private int publicationYear;

    @NotEmpty(message = "ISBN cannot be empty.")
    private String isbn;

    @Min(value = 0, message = "Price must be positive.")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category must be selected.")
    private Category category;

    public Book() {}

    public Book(String title, String author, int publicationYear, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
    }

    public Long getId() {
        return id;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "', year=" + publicationYear + "}";
    }
}

