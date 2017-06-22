package com.timemachine.springcloud.service;

import com.timemachine.springcloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by tony on 2017/6/21.
 */
@FeignClient("springcloud-provider-user-service")
public interface UserService {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    List<User> list();

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    User login(@RequestParam("name")  String name, @RequestParam("password") String password);



}
