package com.study.Dao;

import com.study.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User findUserById(String userId) {
        String sql = "select * from tb_user_base where uid=?";
//        List<User> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        User user2 = jdbcTemplate.queryForObject(sql, new String[]{userId}, new BeanPropertyRowMapper<>(User.class));
        return user2;
    }

}
