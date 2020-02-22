package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.YbProductStock;
import com.example.demo.service.UserService;
import com.example.demo.service.YbProductStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private YbProductStockService ybProductStockService;

    @RequestMapping("/allUser")
    public List<User> getAllUser() throws Exception{
        return userService.getAllUser();
    }
    @RequestMapping("/getUserById")
    public User getUserById(int id) throws Exception{
        return userService.getUserById(id);
    }

    @RequestMapping("/productStockPage")
    public Page<YbProductStock> getProductStockPage(){
        Page<YbProductStock> list =  ybProductStockService.getProducStock("SZ_AA", 1, 10);
        return list;
    }
}
