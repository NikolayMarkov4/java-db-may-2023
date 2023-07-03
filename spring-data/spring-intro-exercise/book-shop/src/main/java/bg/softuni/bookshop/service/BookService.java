package bg.softuni.bookshop.service;

import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.domain.model.BookPrintInformation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    boolean isDataSeeded();

    void seedBooks(List<Book> authors);

    List<Book> getAllBooksAfterYear(LocalDate date);

    List<Book> getAllBooksBeforeYear(LocalDate date);

    List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> getAllByAgeRestriction(String ageRestriction);

    List<Book> getAllByEditionTypeAndCopiesOver(String editionType, Integer copiesToCompare);

    List<Book> getAllByPriceNotBetween(BigDecimal lowBoundary, BigDecimal upperBoundary);

    List<Book> getAllNotReleasedInYear(Integer year);

    List<Book> getAllByTitleContaining(String contains);

    List<Book> getAllByAuthorLastNameStartsWith(String prefix);

    Integer getAllByTitleLengthGreaterThan(Integer length);

    List<BookPrintInformation> getAllByBookTitle(String title);

    void increaseCopiesForBookReleasedAfter(Integer addedCopies, LocalDate dateAfter);

    int deleteAllByCopiesLessThan(Integer copies);

    int getBooksCountByAuthorFirstNameAndAuthorLastName(String firstName);

}
