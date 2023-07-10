package com.example.jsonxmlexercise.services.seed;

import com.example.jsonxmlexercise.domain.entities.Category;
import com.example.jsonxmlexercise.domain.entities.Product;
import com.example.jsonxmlexercise.domain.entities.User;
import com.example.jsonxmlexercise.domain.models.CategoryImportModel;
import com.example.jsonxmlexercise.domain.models.ProductImportModel;
import com.example.jsonxmlexercise.domain.models.UserImportModel;
import com.example.jsonxmlexercise.repositories.CategoryRepository;
import com.example.jsonxmlexercise.repositories.ProductRepository;
import com.example.jsonxmlexercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.example.jsonxmlexercise.constants.Paths.*;
import static com.example.jsonxmlexercise.constants.Utils.GSON;
import static com.example.jsonxmlexercise.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() > 0) return;

        final FileReader fileReader = new FileReader(USERS_JSON_PATH.toFile());

        final List<User> usersToSave =
                Arrays.stream(GSON.fromJson(fileReader, UserImportModel[].class))
                        .map(userImportModel -> MODEL_MAPPER.map(userImportModel, User.class))
                        .toList();

        this.userRepository.saveAll(usersToSave);

        fileReader.close();
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() > 0) return;

        final FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH.toFile());

        final List<Category> categoriesToSave =
                Arrays.stream(GSON.fromJson(fileReader, CategoryImportModel[].class))
                        .map(categoryImportModel -> MODEL_MAPPER.map(categoryImportModel, Category.class))
                        .toList();

        this.categoryRepository.saveAll(categoriesToSave);

        fileReader.close();
    }

    @Override
    public void seedProducts() throws IOException {
        if (this.productRepository.count() > 0) return;

        final FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toFile());

        final List<Product> productsToSave = Arrays.stream(GSON.fromJson(fileReader, ProductImportModel[].class))
                .map(productImportModel -> MODEL_MAPPER.map(productImportModel, Product.class))
                .map(this::setRandomCategories)
                .map(this::setRandomBuyer)
                .map(this::setRandomSeller)
                .toList();

        this.productRepository.saveAll(productsToSave);

        fileReader.close();
    }

    private Product setRandomSeller(Product product) {
        User seller = this.userRepository
                .getRandomEntity()
                .orElseThrow(NoSuchElementException::new);

        while (product.getBuyer() != null && product.getBuyer().getId().equals(seller.getId())) {
            seller = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);
        }

        product.setSeller(seller);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(750)) > 0) {
            final User buyer = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);

            product.setBuyer(buyer);
        }

        return product;
    }

    private Product setRandomCategories(Product product) {
        final Random random = new Random();

        long countOfCategories = random.nextLong(this.categoryRepository.count() - 2);

        Set<Category> categories = new HashSet<>();

//        IntStream.range(0, countOfCategories)
//                .forEach(value -> {
//                    categories.add(this.categoryRepository
//                            .getRandomEntity()
//                            .orElseThrow(NoSuchElementException::new));
//                });

        for (int i = 0; i < countOfCategories; i++) {
            categories.add(this.categoryRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new));
        }

        product.setCategories(categories);

        return product;
    }
}
