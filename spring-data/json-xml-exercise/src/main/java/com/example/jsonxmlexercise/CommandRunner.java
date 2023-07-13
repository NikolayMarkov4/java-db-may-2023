package com.example.jsonxmlexercise;

import com.example.jsonxmlexercise.services.user.UserService;
import com.example.jsonxmlexercise.services.category.CategoryService;
import com.example.jsonxmlexercise.services.product.ProductService;
import com.example.jsonxmlexercise.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public CommandRunner(SeedService seedService, ProductService productService, CategoryService categoryService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAll("XML");
        this.productService.getProductsInRangeWithNoBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        this.userService.getUsersAndSoldProductsWrapper();
        this.userService.getUsersAndSoldProducts();
        this.categoryService.getCategorySummary();
    }
}
