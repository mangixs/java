package com.example.demo.controller;

import com.example.demo.dao.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/allUser")
    public List<User> getAllUser() throws Exception{
        return userService.getAllUser();
    }
    @RequestMapping("/getUserById")
    public User getUserById(int id) throws Exception{
        return userService.getUserById(id);
    }
}
