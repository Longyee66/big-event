package com.bigevent.service;

import com.bigevent.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {

    /**
     * 注册用户
     * @param username
     * @param password
     */
    void addUser(String username, String password);

    /**
     * 查询用户
     * @param map
     * @return
     */
    User getByUser(Map map);

    //根据用户名和密码查询
    User login(String username, String password);

    /**
     * 查询用户信息
     * @return
     */
    User getUserInfo(String username);
}
