package com.example.jsonxmlexercise.repositories;

import com.example.jsonxmlexercise.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from `json-xml-exercise`.users order by  RAND() LIMIT 1", nativeQuery = true)
    Optional<User> getRandomEntity();

    List<User> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName();
}
