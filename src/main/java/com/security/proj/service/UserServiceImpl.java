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
        Set<Role> roles = new HashSet<>();
        switch (strRole) {
            case "MODERATOR":
                roles.add(Role.MODERATOR);
                break;
            case "SUPER_ADMIN":
                roles.add(Role.SUPER_ADMIN);
                break;
            case "SIMPLE_USER":
                roles.add(Role.SIMPLE_USER);
                break;
        }
        user.setRoles(roles);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public void deleteUser(String username) throws Exception {
        userRepository.deleteByUsername(username);
    }

    @Override
    public User updateUser(String username, String fullname) throws Exception {
        User user = userRepository.findByUsername(username);
        if (fullname != null || fullname.isEmpty()) {
            user.setFullname(user.getFullname());
        } else user.setFullname(fullname);
        return user;
    }
}