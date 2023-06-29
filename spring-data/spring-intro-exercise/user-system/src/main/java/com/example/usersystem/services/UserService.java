package com.example.usersystem.services;

import com.example.usersystem.domain.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> getAllUsersByEmailProvider(String provider);

    void deactivateInactiveUsers(Date date);

    void save(User user);

    long getUsersCount();

    List<String> getUserNamesAndAgeByAgeRange(int lowBound, int highBound);

}