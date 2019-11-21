package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper2 {
    @Select("select * from yb_users")
    List<User> getAllUsers();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "u"),
            @Result(property = "email", column = "e")
    })
    @Select("select username as u,email as e,id as id from yb_users where id=#{id}")
    User getUserById(Long id);

    @Select("select * from yb_users where username like concat('%',#{name},'%')")
    List<User> getUsersByName(String name);

    @Insert({"insert into user(username,password,email) values(#{username},#{password}),#{email}"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addUser(User user);

    @Update("update yb_users set username=#{username},email=#{email} where id=#{id}")
    Integer updateUserById(User user);

    @Delete("delete from yb_users where id=#{id}")
    Integer deleteUserById(Integer id);
}