package com.example.jsonxmlexercise.domain.models.product;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWithCountModel {

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "product")
    private List<ProductBasicInfoModel> products;

    public static ProductsSoldWithCountModel productsSoldWithCountDto(List<ProductBasicInfoModel> products) {
        return new ProductsSoldWithCountModel(products.size(), products);
    }
}