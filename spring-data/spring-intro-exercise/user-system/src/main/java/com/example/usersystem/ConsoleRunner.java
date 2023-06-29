package com.example.usersystem;

import com.example.usersystem.domain.entities.User;
import com.example.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public ConsoleRunner(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(final String... args) {
        if (this.userService.getUsersCount() == 0L) {
            addUsers();
        }
        printAllUsersByEmailProvider();
        deactivateUsersInactiveSinceDate(new Date());
        printAllUsersByAgeRange();
    }

    private void printAllUsersByAgeRange() {
        this.userService.getUserNamesAndAgeByAgeRange(20, 25)
                .forEach(System.out::println);
    }

    private void deactivateUsersInactiveSinceDate(final Date date) {
        this.userService.deactivateInactiveUsers(date);
    }

    private void printAllUsersByEmailProvider() {
        this.userService.getAllUsersByEmailProvider("abv.bg")
                .forEach(user -> System.out.println(user.getUserName() + " " + user.getEmail()));
    }

    private void addUsers() {
        for (int i = 1; i <= 1000; i++) {
            this.userService.save(User.builder()
                    .userName("username" + i)
                    .password("pasSword%" + i)
                    .email("mail" + i + "x@abv.bg")
                    .age(i % 120 + 1)
                    .firstName("First" + i)
                    .lastName("Last" + i)
                    .registeredOn(new Date())
                    .lastTimeLoggedIn(new Date())
                    .isDeleted(false)
                    .build());
        }
    }

}