package com.example.jsonxmlexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersWithSoldProductsWrapperModel {

    private Integer count;

    private List<UserWithSoldProducts> users;

    public UsersWithSoldProductsWrapperModel(List<UserWithSoldProducts> users) {
        this.users = users;
        this.count = users.size();
    }
}
