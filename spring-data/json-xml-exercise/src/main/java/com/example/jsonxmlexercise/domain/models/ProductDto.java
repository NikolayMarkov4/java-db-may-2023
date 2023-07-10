package com.example.jsonxmlexercise.domain.models;

import com.example.jsonxmlexercise.domain.entities.User;
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


    public static ProductsSoldWithCountDto toProductsSoldWithCountDto(Set<ProductDto> sellingProducts) {
        List<ProductBasicInfoModel> productBasicInfoModelStream = sellingProducts.stream()
                .map(ProductDto::toProductBasicInfoModel)
                .toList();

        return ProductsSoldWithCountDto.productsSoldWithCountDto(productBasicInfoModelStream);
    }

    private static ProductBasicInfoModel toProductBasicInfoModel(ProductDto productDto) {
        return new ProductBasicInfoModel(productDto.getName(), productDto.getPrice());
    }
}
