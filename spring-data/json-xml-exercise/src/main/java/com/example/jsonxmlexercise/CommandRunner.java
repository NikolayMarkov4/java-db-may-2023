package com.example.jsonxmlexercise;

import com.example.jsonxmlexercise.services.UserService;
import com.example.jsonxmlexercise.services.product.ProductService;
import com.example.jsonxmlexercise.services.seed.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;

    public CommandRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAll();
        this.productService.getProductsInRangeWithNoBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        this.userService.getSuccUsers();
        this.productService.getCategorySummary();
    }
}
