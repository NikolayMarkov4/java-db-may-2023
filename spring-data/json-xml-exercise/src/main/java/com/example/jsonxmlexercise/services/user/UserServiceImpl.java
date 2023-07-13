package com.example.jsonxmlexercise.services.user;

import com.example.jsonxmlexercise.domain.models.user.UserModel;
import com.example.jsonxmlexercise.domain.models.user.UserWithSoldProducts;
import com.example.jsonxmlexercise.domain.models.user.UserWithSoldProductsModel;
import com.example.jsonxmlexercise.domain.models.user.wrappers.UsersWithSoldProductsWrapperModel;
import com.example.jsonxmlexercise.domain.models.user.wrappers.UserWithSoldProductsWrapperModel;
import com.example.jsonxmlexercise.repositories.UserRepository;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.jsonxmlexercise.constants.Paths.*;
import static com.example.jsonxmlexercise.constants.Utils.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersWithSoldProductsWrapperModel getUsersAndSoldProductsWrapper() throws IOException, JAXBException {
        final List<UserWithSoldProducts> userWithSoldProducts = userRepository
                .findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName()
                .stream()
                .map(u -> MODEL_MAPPER.map(u, UserModel.class))
                .map(UserModel::toUserWithProductModel)
                .toList();

        final UsersWithSoldProductsWrapperModel wrapper =
                new UsersWithSoldProductsWrapperModel(userWithSoldProducts);

        writeIntoJsonFile(wrapper, FOURTH_JSON_PATH);
        writeIntoXmlFile(wrapper, FOURTH_XML_PATH);

        return wrapper;
    }

    @Override
    public List<UserWithSoldProductsModel> getUsersAndSoldProducts() throws IOException, JAXBException {
        final List<UserWithSoldProductsModel> users = userRepository
                .findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName()
                .stream()
                .map(u -> MODEL_MAPPER.map(u, UserWithSoldProductsModel.class))
                .toList();

        final UserWithSoldProductsWrapperModel wrapper = new UserWithSoldProductsWrapperModel().of(users);

        writeIntoJsonFile(users, SECOND_JSON_PATH);
        writeIntoXmlFile(wrapper, SECOND_XML_PATH);

        return users;
    }
}
