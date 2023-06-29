package bg.softuni.bookshop.service;

import bg.softuni.bookshop.domain.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    boolean isDataSeeded();

    void seedBooks(List<Book> authors);

    List<Book> getAllBooksAfterYear(LocalDate date);

    List<Book> getAllBooksBeforeYear(LocalDate date);

    List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

}

