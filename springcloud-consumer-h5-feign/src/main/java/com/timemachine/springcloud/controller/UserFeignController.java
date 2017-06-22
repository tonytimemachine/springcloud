package com.timemachine.springcloud.controller;

import com.timemachine.springcloud.entity.User;
import com.timemachine.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User Feign Controller
 *
 * @author Liuguanglei liugl@ekeyfund.com
 * @create 2017-06-下午1:50
 */
@RestController
public class UserFeignController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/list")
    public List<User> list(){

        return userService.list();
    }

    @GetMapping("/login")
    public User login(@RequestParam String name,@RequestParam String password){

        return userService.login(name,password);
    }
}
