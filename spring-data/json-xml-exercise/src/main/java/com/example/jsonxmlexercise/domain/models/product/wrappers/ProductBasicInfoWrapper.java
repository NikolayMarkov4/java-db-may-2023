package com.example.jsonxmlexercise.domain.models.product.wrappers;

import com.example.jsonxmlexercise.domain.models.product.ProductBasicInfoWithSellerFullName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBasicInfoWrapper {

    @XmlElement(name = "product")
    private List<ProductBasicInfoWithSellerFullName> products;

}
