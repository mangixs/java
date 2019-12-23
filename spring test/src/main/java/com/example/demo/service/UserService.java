package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser() throws Exception;

    User getUserById(int attr1) throws Exception;

    void addUser(User user) throws Exception;

    void updateUserById(User user) throws Exception;

    void deleteUserById(int id) throws Exception;
}
