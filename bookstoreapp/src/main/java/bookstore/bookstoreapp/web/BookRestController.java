package bookstore.bookstoreapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bookstore.bookstoreapp.model.Book;
import bookstore.bookstoreapp.repository.BookRepository;
import bookstore.bookstoreapp.repository.CategoryRepository;

@RestController
public class BookRestController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookRestController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "/books")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping(value = "/books/{id}")
    public @ResponseBody Book getBookById(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
}

