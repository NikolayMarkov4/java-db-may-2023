package com.example.usersystem.repositories;

import com.example.usersystem.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByNameEndingWith(String input);
}