package com.security.proj.service;

import com.security.proj.model.Role;
import com.security.proj.model.User;
import com.security.proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(String name, String password, String fullname, String strRole) throws Exception {
        if(name == null || password == null || fullname == null || strRole == null) {
            throw new Exception("No one field cannot be empty");
        }
        User user = new User(name, password, fullname);
        switch (strRole) {
            case "MODERATOR":
                user.setRole(Role.MODERATOR);
                break;
            case "SUPER_ADMIN":
                user.setRole(Role.SUPER_ADMIN);
                break;
            case "SIMPLE_USER":
                user.setRole(Role.SIMPLE_USER);
                break;
        }
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public void deleteUser(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.deleteByUsername(username);
        } else {
            throw new Exception("User does not found");
        }
    }

    @Override
    public User updateUser(String username, String fullname) {
        User user = userRepository.findByUsername(username);
        if (fullname != null || fullname.isEmpty()) {
            user.setFullname(user.getFullname());
        } else user.setFullname(fullname);
        return user;
    }
}