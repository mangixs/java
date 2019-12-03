package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.mapper.UserMapper2;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper2 UserMapper2;

    @Autowired
    private UserMapper UserMapper;

    @RequestMapping("/index")
    public User getSingleUser(Long id){
        User user =   UserMapper2.getUserById(id);
        return user;
    }
    @RequestMapping("/getAllUser")
    public List<User> getAllUser(){
        return UserMapper2.getAllUsers();
    }
    @RequestMapping("/getAllUser2")
    public List<User> getAllUser2(){
        return UserMapper.getAllUser();
    }
}
