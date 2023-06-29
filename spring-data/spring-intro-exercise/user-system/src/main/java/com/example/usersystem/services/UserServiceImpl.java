package com.example.usersystem.services;

import com.example.usersystem.domain.entities.User;
import com.example.usersystem.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsersByEmailProvider(final String provider) {
        return this.userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    public void deactivateInactiveUsers(final Date date) {
        this.userRepository
                .findAllByLastTimeLoggedInBefore(date)
                .forEach(user -> user.setIsDeleted(true));
    }

    @Override
    public void save(final User user) {
        this.userRepository.save(user);
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public List<String> getUserNamesAndAgeByAgeRange(final int lowBound, final int highBound) {
        return this.userRepository
                .findAllByAgeBetweenOrderByAge(lowBound, highBound)
                .stream()
                .map(User::getFullNameAndAge)
                .collect(Collectors.toList());
    }
}