package bookstore.bookstoreapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Book getBookById(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
}

