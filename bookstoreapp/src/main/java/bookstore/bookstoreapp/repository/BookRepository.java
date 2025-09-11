package bookstore.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bookstore.bookstoreapp.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    
    List<Book> findByAuthor(String author);

    List<Book> findByTitleContaining(String keyword);

    List<Book> findByPublicationYearOrderByTitleAsc(int year);

    List<Book> findByAuthorIgnoreCase(String author);
}
