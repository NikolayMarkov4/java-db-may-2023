package com.example.jsonxmlexercise.services;

import com.example.jsonxmlexercise.domain.models.UserModel;
import com.example.jsonxmlexercise.domain.models.UserWithSoldProducts;
import com.example.jsonxmlexercise.domain.models.UsersWithSoldProductsWrapperModel;
import com.example.jsonxmlexercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.jsonxmlexercise.constants.Paths.FOURTH_JSON_PATH;
import static com.example.jsonxmlexercise.constants.Utils.MODEL_MAPPER;
import static com.example.jsonxmlexercise.constants.Utils.writeIntoJsonFile;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersWithSoldProductsWrapperModel getSuccUsers() throws IOException {
        final List<UserWithSoldProducts> userWithSoldProducts = userRepository
                .findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName()
                .stream()
                .map(u -> MODEL_MAPPER.map(u, UserModel.class))
                .map(UserModel::toUserWithProductModel)
                .toList();

        final UsersWithSoldProductsWrapperModel response =
                new UsersWithSoldProductsWrapperModel(userWithSoldProducts);

        writeIntoJsonFile(response, FOURTH_JSON_PATH);

        return response;
    }

//    @Override
//    public List<UserWithSoldProductsModel> getSuccUsers2() throws IOException {
//        final List<UserWithSoldProductsModel> users = userRepository
//                .findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName()
//                .stream()
//                .map(u -> MODEL_MAPPER.map(u, UserWithSoldProductsModel.class))
//                .toList();
//
//        writeIntoJsonFile(users, SECOND_JSON_PATH);
//
//        return users;
//    }
}
