package com.example.jsonxmlexercise.services.product;

import com.example.jsonxmlexercise.domain.models.CategorySummaryModel;
import com.example.jsonxmlexercise.domain.models.ProductBasicInfoWithSellerFullName;
import com.example.jsonxmlexercise.repositories.CategoryRepository;
import com.example.jsonxmlexercise.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.example.jsonxmlexercise.constants.Paths.FIRST_JSON_PATH;
import static com.example.jsonxmlexercise.constants.Paths.THIRD_JSON_PATH;
import static com.example.jsonxmlexercise.constants.Utils.writeIntoJsonFile;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<ProductBasicInfoWithSellerFullName> getProductsInRangeWithNoBuyer(BigDecimal lowBoundary, BigDecimal highBoundary) throws IOException {
        final List<ProductBasicInfoWithSellerFullName> products =
                this.productRepository
                        .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lowBoundary, highBoundary)
                        .stream()
                        .map(ProductBasicInfoWithSellerFullName::getFromProduct)
                        .toList();

        writeIntoJsonFile(products, FIRST_JSON_PATH);

        return products;
    }

    @Override
    public List<CategorySummaryModel> getCategorySummary() throws IOException {
        final List<CategorySummaryModel> categorySummary = this.categoryRepository.getCategorySummary();

        writeIntoJsonFile(categorySummary, THIRD_JSON_PATH);

        return categorySummary;
    }
}
