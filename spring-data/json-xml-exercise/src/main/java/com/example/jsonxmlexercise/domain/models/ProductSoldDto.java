package com.example.jsonxmlexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSoldDto {

    private String name;

    private BigDecimal price;

    private String buyerFirstName;

    private String buyerLastName;

}
