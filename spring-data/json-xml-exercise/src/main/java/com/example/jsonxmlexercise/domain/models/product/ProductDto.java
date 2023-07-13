package com.example.jsonxmlexercise.domain.models.product;

import com.example.jsonxmlexercise.domain.entities.User;
import com.example.jsonxmlexercise.domain.models.category.CategoryModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductDto {

    private String name;
    private BigDecimal price;
    private User buyer;
    private User seller;
    private Set<CategoryModel> categories;


    public static ProductsSoldWithCountModel toProductsSoldWithCountDto(Set<ProductDto> sellingProducts) {
        List<ProductBasicInfoModel> productBasicInfoModelStream = sellingProducts.stream()
                .map(ProductDto::toProductBasicInfoModel)
                .toList();

        return ProductsSoldWithCountModel.productsSoldWithCountDto(productBasicInfoModelStream);
    }

    private static ProductBasicInfoModel toProductBasicInfoModel(ProductDto productDto) {
        return new ProductBasicInfoModel(productDto.getName(), productDto.getPrice());
    }
}
