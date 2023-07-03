package bg.softuni.bookshop.repositories;

import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.domain.enums.AgeRestriction;
import bg.softuni.bookshop.domain.enums.EditionType;
import bg.softuni.bookshop.domain.model.BookPrintInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// creating the repository interface will create table in the database
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findAllByReleaseDateAfter(LocalDate date);

    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copiesToCompare);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowBoundary, BigDecimal upperBoundary);

    List<Book> findAllByTitleContaining(String contains);

    List<Book> findAllByReleaseDateBeforeAndReleaseDateAfter(LocalDate date1, LocalDate date2);

    List<Book> findAllByAuthorLastNameStartsWith(String prefix);

    @Query("Select count(b) from Book b where length(b.title)  > :length")
    Integer findAllByTitleLengthGreaterThan(Integer length);

    //    @Query("select new bg.softuni.bookshop.domain.model.BookPrintInformation(b.title, b.editionType, b.ageRestriction,b.price)" +
    //            " from Book b where b.title = :title")
    List<BookPrintInformation> findAllByTitle(String title);

    @Query(value = "SELECT * FROM book_shop_system.books AS b WHERE YEAR(b.release_date) != 2000", nativeQuery = true)
    List<Book> findAllByReleaseDateYearNot(Integer year);

    @Modifying
    @Transactional
    @Query("update Book b set b.copies = b.copies + :copiesAdded where b.releaseDate > :date")
    int updateBooksCopies(Integer copiesAdded, LocalDate date);

    @Transactional
    int deleteAllByCopiesLessThan(Integer copies);

    @Procedure(value = "usp_get_book_written_by")
    int getBooksCountByAuthorFirstNameAndAuthorLastName(String fullName);
}
