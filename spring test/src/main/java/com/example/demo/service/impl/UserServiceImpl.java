package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() throws Exception {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(int attr1) throws Exception {
        return userMapper.getUserById(attr1);
    }

    @Override
    public void addUser(User user) throws Exception {
        userMapper.addUser(user);
    }

    @Override
    public void updateUserById(User user) throws Exception {
        userMapper.updateUserById(user);
    }

    @Override
    public void deleteUserById(int id) throws Exception {
        userMapper.deleteUserById(id);
    }
}
