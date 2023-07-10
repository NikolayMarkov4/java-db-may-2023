package com.example.jsonxmlexercise.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductsSoldWithCountDto {

    private Integer count;

    private List<ProductBasicInfoModel> products;

    public static ProductsSoldWithCountDto productsSoldWithCountDto(List<ProductBasicInfoModel> products) {
        return new ProductsSoldWithCountDto(products.size(), products);
    }
}