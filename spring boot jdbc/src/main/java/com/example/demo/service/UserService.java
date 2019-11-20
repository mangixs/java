package com.example.demo.service;

import com.example.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int addUser(User user) {
        return jdbcTemplate.update("insert into yb_users (username,email,password) values (?,?);", user.getUsername(), user.getEmail(), user.getPassword());
    }
    public int deleteUserById(Long id) {
        return jdbcTemplate.update("delete from yb_users where id=?", id);
    }
    public int updateUserById(User user) {
        return jdbcTemplate.update("update yb_users set username=?,email=? where id=?", user.getUsername(), user.getEmail(),user.getId());
    }
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from yb_users", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                int id = resultSet.getInt("id");
                User user = new User();
                user.setEmail(email);
                user.setUsername(username);
                user.setId(id);
                return user;
            }
        });
    }
    public User singleQuery(int id) {
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject("select * from yb_users where id = ?", mapper, id);
        return user;
    }
}
