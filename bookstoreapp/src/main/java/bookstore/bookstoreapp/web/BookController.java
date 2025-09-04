package bookstore.bookstoreapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookstore.bookstoreapp.model.Book;
import bookstore.bookstoreapp.model.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

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
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

   
    @RequestMapping("/search")
    public String searchBooks(@RequestParam("author") String author, Model model) {
        model.addAttribute("books", repository.findByAuthor(author));
        return "booklist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "addbook"; 
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateBook(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }
}
