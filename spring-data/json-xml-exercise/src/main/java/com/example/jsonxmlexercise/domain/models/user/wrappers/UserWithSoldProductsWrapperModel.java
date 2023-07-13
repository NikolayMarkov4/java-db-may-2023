package com.example.jsonxmlexercise.domain.models.user.wrappers;

import com.example.jsonxmlexercise.domain.models.product.wrappers.ProductsSoldWrapper;
import com.example.jsonxmlexercise.domain.models.user.UserWithSoldProductsModel;
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
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsWrapperModel {

    @XmlElement(name = "user")
    private List<UserWithSoldProductsXmlModel> users;


    public UserWithSoldProductsWrapperModel of(List<UserWithSoldProductsModel> users) {
        final List<UserWithSoldProductsXmlModel> userWithSoldProductsXmlModels = users.stream()
                .map(user -> new UserWithSoldProductsXmlModel(user.getFirstName(),
                        user.getLastName(),
                        new ProductsSoldWrapper(user.getBoughtProducts())))
                .toList();

        return new UserWithSoldProductsWrapperModel(userWithSoldProductsXmlModels);
    }


}
