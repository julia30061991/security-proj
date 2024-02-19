package com.security.proj.service;

import com.security.proj.model.User;

import java.util.List;

public interface UserService {

    User getByUsername(String username);

    List<User> getAllUsers();

    User createUser(String name, String password, String fullname, String strRole) throws Exception;

    void deleteUser(String username) throws Exception;

    User updateUser(String username, String fullname) throws Exception;
}