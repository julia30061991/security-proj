package com.security.proj.controller;

import com.security.proj.model.User;
import com.security.proj.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/user")
public class RestController {

    @Autowired
    private final UserServiceImpl userService;

    public RestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //создание (регистрация) пользователя и добавление в бд
    @PostMapping("create")
    public ResponseEntity<Object> createUser(@RequestParam String username, String password, String fullname, String strRole) {
        try {
            User user = userService.createUser(username, password, fullname, strRole);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('SIMPLE_USER')")
    @GetMapping("show")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @Transactional
    @DeleteMapping("delete")
    public ResponseEntity<Object> deleteUser(@RequestParam String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @Transactional
    @PatchMapping("update")
    public ResponseEntity<Object> updateUser(@RequestParam String username, @RequestParam String fullname) {
        try {
            User user = userService.updateUser(username, fullname);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}