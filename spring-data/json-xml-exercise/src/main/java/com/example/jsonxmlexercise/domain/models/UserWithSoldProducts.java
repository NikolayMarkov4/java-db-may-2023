package com.example.jsonxmlexercise.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserWithSoldProducts {

    private String firstName;
    private String lastName;
    private Integer age;
    private ProductsSoldWithCountDto soldProducts;
}
