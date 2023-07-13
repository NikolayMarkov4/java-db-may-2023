package com.example.jsonxmlexercise.domain.models.product.wrappers;

import com.example.jsonxmlexercise.domain.models.product.ProductImportModel;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWrapperImportModel {

    @XmlElement(name = "product")
    private List<ProductImportModel> products;
}
