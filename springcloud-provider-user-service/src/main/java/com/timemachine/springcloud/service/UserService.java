package com.timemachine.springcloud.service;


import com.timemachine.springcloud.entity.User;

import java.util.List;

/**
 * Created by tony on 2017/6/19.
 */
public interface UserService {


    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    public User login(String name, String password);


    /**
     * 注册
     * @param user
     * @return
     */
    public User register(User user);


    /**
     * 注销
     * @param user
     * @return
     */
    void writeOff(User user);

    /**
     * 当前用户是否已经存在
     * @param user
     * @return
     */
    boolean isExists(User user);


     List<User> getAllUser();


     User getUserById(Long id);





}
