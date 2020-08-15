package com.study;


import com.study.Dao.UserDao;
import com.study.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class RedisStudyApplicationTests {

    @Autowired
    UserDao dao;


    @Test
    public void test0() throws Exception {
        User user = dao.findUserById("1");
        System.out.println(user.getName());
    }

}
