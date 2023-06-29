package bg.softuni.bookshop;

import bg.softuni.bookshop.service.AuthorService;
import bg.softuni.bookshop.service.BookService;
import bg.softuni.bookshop.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// after suc compilation of our program run method will be run
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAllData();
//        this.bookService.getAllBooksAfterYear(LocalDate.of(1999, 1, 1));
        this.authorService.getAllAuthorsWithBooksBeforeYear(LocalDate.of(1991, 1, 1));
        this.authorService.getAllAuthorsOrederByBooksDesc();
    }

}
