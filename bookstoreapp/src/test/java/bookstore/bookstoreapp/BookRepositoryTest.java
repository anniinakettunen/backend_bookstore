package bookstore.bookstoreapp;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bookstore.bookstoreapp.model.Book;
import bookstore.bookstoreapp.model.Category;
import bookstore.bookstoreapp.repository.BookRepository;
import bookstore.bookstoreapp.repository.CategoryRepository;



@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createNewBook() {
        Category category = new Category("Fantasy");
        categoryRepository.save(category);

        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "ISBN123456789", 19.99, category);
        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = bookRepository.findByTitle("The Hobbit");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("J.R.R. Tolkien");
    }

    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByTitle("The Hobbit");
        Book book = books.get(0);
        bookRepository.delete(book);

        List<Book> newBooks = bookRepository.findByTitle("The Hobbit");
        assertThat(newBooks).hasSize(0);
    }
}

