package com.example.jsonxmlexercise.services.seed;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface SeedService {
    void seedUsers(String type) throws IOException, JAXBException;

    void seedCategories(String type) throws IOException, JAXBException;

    void seedProducts(String type) throws IOException, JAXBException;

    default void seedAll(String type) throws IOException, JAXBException {
        seedUsers(type);
        seedCategories(type);
        seedProducts(type);
    }
}
