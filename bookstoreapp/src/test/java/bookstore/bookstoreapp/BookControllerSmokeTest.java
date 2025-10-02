package bookstore.bookstoreapp;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bookstore.bookstoreapp.web.BookController;

@SpringBootTest
public class BookControllerSmokeTest {

    @Autowired
    private BookController bookController;

    @Test
    public void controllerIsNotNull() {
        assertThat(bookController).isNotNull();
    }
}
