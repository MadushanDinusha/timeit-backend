package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserRole(String name);
    Long getUserId(String name);
    List<User> getAll();
    User saveUser(User user);
    String getUserNameByFullName(String fullName);
    Optional<User> getUserById(long id);
    int getUserCount();
}
