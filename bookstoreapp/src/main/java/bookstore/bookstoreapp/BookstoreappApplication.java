package bookstore.bookstoreapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.bookstoreapp.model.Book;
import bookstore.bookstoreapp.model.BookRepository;

@SpringBootApplication
public class BookstoreappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreappApplication.class, args);
	}

	@Bean
public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
	  
            Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 15.99);
            Book book2 = new Book("1984", "George Orwell", 1949, "978-0451524935", 12.99);
            
            repository.save(book1);
            repository.save(book2);

			
	};
}

}
