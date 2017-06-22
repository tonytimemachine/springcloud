package com.timemachine.springcloud.controller;

import com.timemachine.springcloud.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Login Controller
 *
 * @author Liuguanglei liugl@ekeyfund.com
 * @create 2017-06-上午12:55
 */
@RestController
public class UserController {



    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RestTemplate restTemplate;




    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping("/login")
    public User login(@RequestParam String name, @RequestParam String password){

        LOGGER.info("call user service login method");
        ResponseEntity<User> responseEntity =this.restTemplate.getForEntity("http://SPRINGCLOUD-PROVIDER-USER-SERVICE/login?name={1},password={2}",User.class,name,password);

        return responseEntity.getBody();
    }

    @GetMapping("/list")

    public List<User> list(){


        User[] users=this.restTemplate.getForObject("http://SPRINGCLOUD-PROVIDER-USER-SERVICE/list",User[].class);
        List<User> userList = Arrays.asList(users);
        return userList;

    }


    @GetMapping("user/get/{id}")
    public User get(@PathVariable Long id){

        return this.restTemplate.getForObject("http://SPRINGCLOUD-PROVIDER-USER-SERVICE/get/id={1}",User.class,id);
    }


    @GetMapping("/log-instance")
    public void logUserInstance(){

        ServiceInstance serviceInstance=this.loadBalancerClient.choose("user-service");

        LOGGER.info("serviceInstance info ---> serviceId is  "+serviceInstance.getServiceId()+" host is "+serviceInstance.getHost()+"port is "+serviceInstance.getPort() );
    }

}
