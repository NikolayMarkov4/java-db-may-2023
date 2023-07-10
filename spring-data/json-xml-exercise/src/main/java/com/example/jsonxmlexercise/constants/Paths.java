package com.example.jsonxmlexercise.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path CATEGORIES_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "categories.json");

    public static final Path USERS_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "users.json");

    public static final Path PRODUCTS_JSON_PATH =
            Path.of("src", "main", "resources", "dbContent", "products.json");


    public static final Path FIRST_JSON_PATH =
            Path.of("src", "main", "resources", "output", "1.json");

    public static final Path SECOND_JSON_PATH =
            Path.of("src", "main", "resources", "output", "2.json");

    public static final Path THIRD_JSON_PATH =
            Path.of("src", "main", "resources", "output", "3.json");

    public static final Path FOURTH_JSON_PATH =
            Path.of("src", "main", "resources", "output", "4.json");
}
