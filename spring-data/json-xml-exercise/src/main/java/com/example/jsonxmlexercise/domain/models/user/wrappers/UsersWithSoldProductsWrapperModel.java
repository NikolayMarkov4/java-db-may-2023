package com.example.jsonxmlexercise.domain.models.user.wrappers;

import com.example.jsonxmlexercise.domain.models.user.UserWithSoldProducts;
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
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsWrapperModel {

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "user")
    private List<UserWithSoldProducts> users;

    public UsersWithSoldProductsWrapperModel(List<UserWithSoldProducts> users) {
        this.users = users;
        this.count = users.size();
    }
}
