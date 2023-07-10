package com.example.jsonxmlexercise.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CategorySummaryModel {
    private String category;
    private Long productsCount;
    private Double averagePrice;
    private BigDecimal totalRevenue;

}
