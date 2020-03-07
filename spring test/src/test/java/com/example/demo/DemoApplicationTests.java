package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Test
    public void usertTest() throws Exception {
        User user = userService.getUserById(32);

    }
}
