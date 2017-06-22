package com.timemachine.springcloud.service.impl;


import com.timemachine.springcloud.entity.User;
import com.timemachine.springcloud.repository.UserRepository;
import com.timemachine.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * User Service Impl
 *
 * @author Liuguanglei tonytimemachine@gmail.com
 * @create 2017-06-下午3:34
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name,password);
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public void writeOff(User user) {
         userRepository.delete(user);
    }

    @Override
    public boolean isExists(User user) {
        return userRepository.findOne(user.getId())!=null?true:false;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }
}
