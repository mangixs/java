package com.example.demo.controller;

import com.example.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.demo.mapper.UserMapperOne;
import com.example.demo.mapper2.UserMapperTwo;

@RestController
public class UserController {
    @Autowired
    private UserMapperOne UserMapperOne;

    @Autowired
    private UserMapperTwo UserMapperTwo;

    @RequestMapping("/getUser1")
    public List<User> getUser1(){
        List<User> user = UserMapperOne.getAllUser();
        return user;
    }
    @RequestMapping("/getUser2")
    public List<User> getUser2(){
        List<User> user = UserMapperTwo.getAllUser();
        return user;
    }

}
