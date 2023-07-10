package com.example.jsonxmlexercise.domain.models;

import com.example.jsonxmlexercise.domain.entities.Product;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductBasicInfoWithSellerFullName {

    private String name;

    private BigDecimal price;

    @SerializedName(value = "seller")
    private String sellerFirstName;


    public static ProductBasicInfoWithSellerFullName getFromProduct(Product product) {
        String fullName = product.getSeller().getFirstName() + " " + product.getSeller().getLastName();

        return new ProductBasicInfoWithSellerFullName(product.getName(), product.getPrice(), fullName);
    }
}
