package bg.softuni.bookshop.repositories;

import bg.softuni.bookshop.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// creating the repository interface will create table in the database
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("Select a from Author a order by size(a.books) ")
    List<Author> findAllDistinctOrderByBooks();

    List<Author> findAllByFirstNameEndingWith(String suffix);

}
