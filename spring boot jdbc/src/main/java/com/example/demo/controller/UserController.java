package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/user")
    public void user(){
        List<User> user = userService.getAllUsers();
        for(User a : user) {
            System.out.print(a.getUsername());
        }
    }
    @RequestMapping("/list")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
    @RequestMapping("/one")
    public User getDataOne(int id){
        return userService.singleQuery(id);
    }
}
