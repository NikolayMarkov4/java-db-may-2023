package bg.softuni.bookshop.repositories;

import bg.softuni.bookshop.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// creating the repository interface will create table in the database
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findAllByReleaseDateAfter(LocalDate date);

    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);

}
