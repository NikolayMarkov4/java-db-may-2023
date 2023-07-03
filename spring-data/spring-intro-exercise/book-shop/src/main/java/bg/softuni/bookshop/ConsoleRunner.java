package bg.softuni.bookshop;

import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.service.AuthorService;
import bg.softuni.bookshop.service.BookService;
import bg.softuni.bookshop.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Collectors;

// after suc compilation of our program run method will be run
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final Scanner scanner;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAllData();

        int taskNumber = scanner.nextInt();

        while (taskNumber != 100) {
            switch (taskNumber) {
                case 1:
                    printAllBooksAfterYear();
                    break;
                case 2:
                    printAllBooksByAuthorName();
                    break;

                default:
                    return;
            }

            taskNumber = scanner.nextInt();
        }

//        this.authorService.getAllAuthorsWithBooksBeforeYear(LocalDate.of(1991, 1, 1));
//        this.authorService.getAllAuthorsOrderByBooksDesc();
//        this.bookService.getAllByAgeRestriction(firstInput);
//        this.bookService.getAllByEditionTypeAndCopiesOver("Gold", 5000);
//        this.bookService.getAllByPriceNotBetween(BigDecimal.valueOf(5), BigDecimal.valueOf(40) );
//        this.bookService.getAllNotReleasedInYear(2000);
//        this.bookService.getAllBooksBeforeYear(LocalDate.of(Integer.parseInt(thirdInput),
//                Integer.parseInt(secondInput),
//                Integer.parseInt(firstInput)));
//        this.authorService.getAllByFirstNameEndingWith("e");
//        this.bookService.getAllByTitleContaining("sK");
//        this.bookService.getAllByAuthorLastNameStartsWith("Ric");
//        this.bookService.getAllByTitleLengthGreaterThan(12);
//        this.bookService.getAllByBookTitle("Things Fall Apart");
//        this.bookService
//                .increaseCopiesForBookReleasedAfter(100, LocalDate.of(2005, 10,12));
//        this.bookService.deleteAllByCopiesLessThan(10000);
//        System.out.println(this.bookService.getBooksCountByAuthorFirstNameAndAuthorLastName("Amanda Rice"));

    }

    private void printAllBooksByAuthorName() {
        System.out.println("Enter Author full name");
        final String[] inputName = this.scanner.nextLine().split(" ");

        this.bookService
                .getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(inputName[0], inputName[1])
                .forEach(b -> System.out.println(b.getBookTitleReleaseDateCopiesFormat()));
    }

    private void printAllBooksAfterYear() {
        System.out.println(this.bookService.getAllBooksAfterYear(LocalDate.of(1999, 1, 1))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("\n")));
    }

}
