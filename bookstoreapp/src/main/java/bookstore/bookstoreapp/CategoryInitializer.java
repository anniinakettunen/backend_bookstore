package bookstore.bookstoreapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import bookstore.bookstoreapp.model.Category;
import bookstore.bookstoreapp.repository.CategoryRepository;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("Fiction"));
            categoryRepository.save(new Category("Science"));
            categoryRepository.save(new Category("History"));
            categoryRepository.save(new Category("Fantasy"));
        }
    }
}
