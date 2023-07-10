package com.example.jsonxmlexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserWithSoldProductsModel {

    private String firstName;

    private String lastName;

    private Set<ProductSoldDto> boughtProducts;

}
