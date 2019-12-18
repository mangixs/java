package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface UserMapper {
    List<User> getAllUser();
    User getUserById(int id);
    void addUser(User user);
    void updateUserById(User user);
    void deleteUserById(int id);
}
