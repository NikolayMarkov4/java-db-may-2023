package com.example.jsonxmlexercise.domain.models;

import com.example.jsonxmlexercise.domain.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserModel {

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDto> sellingProducts;

    public UserWithSoldProducts toUserWithProductModel() {
        return new UserWithSoldProducts(firstName, lastName, age, ProductDto.toProductsSoldWithCountDto(sellingProducts));
    }
}
