package com.example.jsonxmlexercise.domain.models.user.wrappers;

import com.example.jsonxmlexercise.domain.models.product.ProductSoldModel;
import com.example.jsonxmlexercise.domain.models.product.wrappers.ProductsSoldWrapper;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsXmlModel {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductsSoldWrapper boughtProducts;

}
