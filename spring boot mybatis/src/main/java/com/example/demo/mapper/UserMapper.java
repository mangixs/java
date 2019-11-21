package com.example.demo.mapper;

import com.example.demo.model.User;
import java.util.List;

public interface UserMapper {
    List<User> getAllUser();

    Integer addUser(User user);

    Integer updateUserById(User user);

    Integer deleteUserById(Integer id);

}
