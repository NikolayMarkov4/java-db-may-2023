package com.example.jsonxmlexercise.services.product;

import com.example.jsonxmlexercise.domain.models.CategorySummaryModel;
import com.example.jsonxmlexercise.domain.models.ProductBasicInfoWithSellerFullName;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductBasicInfoWithSellerFullName> getProductsInRangeWithNoBuyer(BigDecimal lowBoundary, BigDecimal highBoundary) throws IOException;

    List<CategorySummaryModel> getCategorySummary() throws IOException;
}

