package bg.softuni.bookshop.service;

import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAllAndFlush(books);
    }

    @Override
    public List<Book> getAllBooksAfterYear(LocalDate date) {
        List<Book> allByReleaseDateAfter = this.bookRepository.findAllByReleaseDateAfter(date).get();

        System.out.println(allByReleaseDateAfter.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("\n")));

        return allByReleaseDateAfter;
    }

    @Override
    public List<Book> getAllBooksBeforeYear(LocalDate date) {
        return this.bookRepository
                .findAllByReleaseDateBefore(date)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return null;
    }
}
