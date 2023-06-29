package bg.softuni.bookshop.service;

import bg.softuni.bookshop.domain.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    boolean isDataSeeded();

    void seedCategories(List<Category> authors);

    Category getRandomCategory();

    Set<Category> getRandomCategories();
}

