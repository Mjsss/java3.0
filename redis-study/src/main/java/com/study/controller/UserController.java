package com.study.controller;

import com.study.entity.User;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 查看所有信息
     */
    @GetMapping("/findUserById")
    public User findUserById(String userId) throws Exception {
        return userService.findUserById(userId);
    }

//    /**
//     * 查看姓名
//     */
//    @RequestMapping("/findUserNameById")
//    public String findUserNameById(String userId) {
//        return userService.findUserNameById(userId);
//    }

}
