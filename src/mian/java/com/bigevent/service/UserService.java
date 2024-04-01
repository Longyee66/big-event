package com.bigevent.service;

import com.bigevent.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @return
     */
    User getByUserName(String username);
    /**
     * 注册用户
     * @param username
     * @param password
     */
    void addUser(String username, String password);
}
