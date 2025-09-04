package bookstore.bookstoreapp.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    
    List<Book> findByAuthor(String author);

    List<Book> findByTitleContaining(String keyword);

    List<Book> findByPublicationYearOrderByTitleAsc(int year);

    List<Book> findByAuthorIgnoreCase(String author);
}
