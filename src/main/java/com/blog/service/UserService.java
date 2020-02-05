package com.blog.service;

import com.blog.pojo.User;

public interface UserService {

    /**
     * 登陆
     * @param username
     * @return
     */
    User checkUser(String username);
}
