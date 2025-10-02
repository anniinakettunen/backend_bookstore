package bookstore.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; // Suositeltavampi kuin CrudRepository

import bookstore.bookstoreapp.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title); 

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContaining(String keyword);

    List<Book> findByPublicationYearOrderByTitleAsc(int year);

    List<Book> findByAuthorIgnoreCase(String author);
}
