package com.blog.service.impl;

import com.blog.pojo.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 登陆
     * @param username
     * @return
     */
    @Override
    public User checkUser(String username) {
        return userRepository.findByUsername(username);
    }
}
