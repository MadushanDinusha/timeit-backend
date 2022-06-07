package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.User;
import com.timeit.Skand1s.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public User getUserRole(String name) {
        return userRepository.getUserByUsername(name);

    }

    @Override
    public Long getUserId(String name) {
       User user = userRepository.getUserByUsername(name);
       return user.getId();
    }

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String getUserNameByFullName(String fullName) {
        User user = userRepository.getUserByFullName(fullName);
        return user.getUsername();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public int getUserCount() {
        List<User> users = userRepository.getUserCount();
        return users.size();
    }
}
