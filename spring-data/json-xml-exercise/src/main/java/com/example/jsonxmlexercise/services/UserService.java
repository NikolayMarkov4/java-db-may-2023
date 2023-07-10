package com.example.jsonxmlexercise.services;

import com.example.jsonxmlexercise.domain.models.UserWithSoldProductsModel;
import com.example.jsonxmlexercise.domain.models.UsersWithSoldProductsWrapperModel;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UsersWithSoldProductsWrapperModel getSuccUsers() throws IOException;

//    List<UserWithSoldProductsModel> getSuccUsers2() throws IOException;
}
