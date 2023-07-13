package com.example.jsonxmlexercise.domain.models.product;

import com.example.jsonxmlexercise.domain.entities.Product;
import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBasicInfoWithSellerFullName {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @SerializedName(value = "seller")
    @XmlAttribute(name = "seller")
    private String sellerFirstName;


    public static ProductBasicInfoWithSellerFullName getFromProduct(Product product) {
        String fullName = product.getSeller().getFirstName() + " " + product.getSeller().getLastName();

        return new ProductBasicInfoWithSellerFullName(product.getName(), product.getPrice(), fullName);
    }
}
