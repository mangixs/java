package com.example.demo.controller;

import com.example.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    @Qualifier("jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;
    @Resource(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;

    @GetMapping("/user")
    public List<User> getAllUser() {
        List<User> list = jdbcTemplateOne.query("select * from yb_users", new BeanPropertyRowMapper<>(User.class));
        return list;
    }
    @GetMapping("/user2")
    public List<User> getAllUser2() {
        List<User> list = jdbcTemplateTwo.query("select * from yb_users", new BeanPropertyRowMapper<>(User.class));
        return list;
    }
}
