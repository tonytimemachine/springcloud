package com.timemachine.springcloud.controller;

import com.timemachine.springcloud.entity.User;
import com.timemachine.springcloud.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * UserController
 * Restful API
 * @author Liuguanglei tonytimemachine@gmail.com
 * @create 2017-06-下午11:24
 */
@RestController
public class UserController {



    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private UserService userService;


    @GetMapping(value = "/list")
   public List<User> list(){

        ServiceInstance instance=discoveryClient.getLocalServiceInstance();
        LOGGER.info("call user/list service  host is  "+instance.getHost()+"service_id is "+instance.getServiceId());
        return userService.getAllUser();
   }

   @GetMapping(value = "/login")
   public User login( @RequestParam String name,@RequestParam String password){

       User user=userService.login(name,password);
       return user;
   }

   @PostMapping("/register")
   public String register(@ModelAttribute User user){
       User result =userService.register(user);
       return result!=null?"success":"fail";
   }

   @GetMapping("/get/{id}")
   public User get(@PathVariable Long id){

       return userService.getUserById(id);
   }

   @PutMapping("/update/{id}")
   public String update(@PathVariable Long id,@ModelAttribute User user){

       User updatedUser =userService.getUserById(id);
       updatedUser.setName(user.getName());
       updatedUser.setPassword(user.getPassword());
       updatedUser.setCreateDate(new Date());
       User result= userService.register(updatedUser);
       return result!=null?"success":"fail";

   }


   @DeleteMapping("/delete/{id}")
   public String delete(@PathVariable Long id){

       User user =new User();
       user.setId(id);
       userService.writeOff(user);

       return "success";
   }


}
