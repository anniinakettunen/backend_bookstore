package bookstore.bookstoreapp.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookstore.bookstoreapp.model.Book;
import bookstore.bookstoreapp.model.Category;
import bookstore.bookstoreapp.repository.BookRepository;
import bookstore.bookstoreapp.repository.CategoryRepository;
import jakarta.validation.Valid;


@Controller
public class BookController {

    
    private final BookRepository repository;
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

   @RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(@Valid @ModelAttribute("book") Book book,
                   BindingResult bindingResult,
                   @RequestParam("category.id") Long categoryId,
                   Model model) {

    if (bindingResult.hasErrors()) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    Category category = categoryRepository.findById(categoryId).orElse(null);
    if (category == null) {
        bindingResult.rejectValue("category", "error.book", "Invalid category selected");
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    book.setCategory(category);
    repository.save(book);
    return "redirect:/booklist";
}

    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/booklist";
        }

        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

@RequestMapping(value = "/update", method = RequestMethod.POST)
public String updateBook(@RequestParam("id") Long id,
                         @RequestParam("category.id") Long categoryId,
                         @Valid Book book,
                         BindingResult bindingResult,
                         Model model) {

    if (bindingResult.hasErrors()) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    Category category = categoryRepository.findById(categoryId).orElse(null);
    if (category == null) {
        bindingResult.rejectValue("category", "error.book", "Invalid category selected");
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    Book existingBook = repository.findById(id).orElse(null);
    if (existingBook == null) {
        return "redirect:/booklist";
    }

    existingBook.setTitle(book.getTitle());
    existingBook.setAuthor(book.getAuthor());
    existingBook.setPublicationYear(book.getPublicationYear());
    existingBook.setIsbn(book.getIsbn());
    existingBook.setPrice(book.getPrice());
    existingBook.setCategory(category);

    repository.save(existingBook);
    return "redirect:/booklist";
}

    @RequestMapping("/search")
    public String searchBooks(@RequestParam("author") String author, Model model) {
        model.addAttribute("books", repository.findByAuthor(author));
        return "booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return "redirect:/booklist";
}

}
