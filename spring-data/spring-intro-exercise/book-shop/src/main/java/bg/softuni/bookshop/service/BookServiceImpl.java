package bg.softuni.bookshop.service;

import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.domain.enums.AgeRestriction;
import bg.softuni.bookshop.domain.enums.EditionType;
import bg.softuni.bookshop.domain.model.BookPrintInformation;
import bg.softuni.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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



        return allByReleaseDateAfter;
    }

    @Override
    public List<Book> getAllBooksBeforeYear(LocalDate date) {
        List<Book> books = this.bookRepository
                .findAllByReleaseDateBefore(date)
                .orElseThrow(NoSuchElementException::new);

        books.forEach(b -> System.out.println(b.getBookTitleEditionTypeAndPrice()));

        return books;
    }

    @Override
    public List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }

    @Override
    public List<Book> getAllByAgeRestriction(String ageRestriction) {
        final List<Book> allByAgeRestriction = this.bookRepository
                .findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));

        allByAgeRestriction.forEach(b -> System.out.println(b.getTitle()));

        return allByAgeRestriction;
    }

    @Override
    public List<Book> getAllByEditionTypeAndCopiesOver(String editionType, Integer copiesToCompare) {
        final List<Book> books = this.bookRepository
                .findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf(editionType.toUpperCase()),
                        copiesToCompare);

        books.forEach(b -> System.out.println(b.getTitle()));

        return books;
    }

    @Override
    public List<Book> getAllByPriceNotBetween(BigDecimal lowBoundary, BigDecimal upperBoundary) {
        final List<Book> books = this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(lowBoundary, upperBoundary);

        books.forEach(b -> System.out.println(b.getBookTitleAndPriceFormat()));

        return books;
    }

    // TODO
    @Override
    public List<Book> getAllNotReleasedInYear(Integer year) {
//        final List<Book> allByReleaseDateYearIsNot =
//                this.bookRepository.findAllByReleaseDate_Year(LocalDate.now());
        return null;
    }

    @Override
    public List<Book> getAllByTitleContaining(String contains) {
        final List<Book> books = this.bookRepository.findAllByTitleContaining(contains);

        books.forEach(b -> System.out.println(b.getTitle()));

        return books;
    }

    @Override
    public List<Book> getAllByAuthorLastNameStartsWith(String prefix) {
        final List<Book> books = this.bookRepository.findAllByAuthorLastNameStartsWith(prefix);

        books.forEach(b -> System.out.println(b.getTitle()));

        return books;
    }

    @Override
    public Integer getAllByTitleLengthGreaterThan(Integer length) {
        Integer count = this.bookRepository.findAllByTitleLengthGreaterThan(length);

        System.out.println(count);

        return count;
    }

    @Override
    public List<BookPrintInformation> getAllByBookTitle(String title) {
        final List<BookPrintInformation> allByTitle = this.bookRepository.findAllByTitle(title);

        allByTitle.forEach(System.out::println);

        return allByTitle;
    }

    @Override
    public void increaseCopiesForBookReleasedAfter(Integer addedCopies, LocalDate dateAfter) {
        final List<Book> books = this.bookRepository.findAllByReleaseDateAfter(dateAfter).get();

        books.forEach(book -> book.setCopies(book.getCopies() + addedCopies));

        this.bookRepository.saveAllAndFlush(books);

//        for (Book book : books) {
//            book.setCopies(book.getCopies() + addedCopies);
//            this.bookRepository.saveAndFlush(book);
//        }

        System.out.println(addedCopies * books.size());
    }

    @Override
    public int deleteAllByCopiesLessThan(Integer copies) {
        return this.bookRepository.deleteAllByCopiesLessThan(copies);
    }

    @Override
    public int getBooksCountByAuthorFirstNameAndAuthorLastName(String firstName) {
        return this.bookRepository.getBooksCountByAuthorFirstNameAndAuthorLastName(firstName);
    }
}
