package bookstore.bookstoreapp.repository;

import org.springframework.data.repository.CrudRepository;
import bookstore.bookstoreapp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
