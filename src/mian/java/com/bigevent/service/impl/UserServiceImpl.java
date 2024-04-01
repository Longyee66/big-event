package com.bigevent.service.impl;

import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.User;
import com.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据usernam查询数据
     * @param username
     * @return
     */
    @Override
    public User getByUserName(String username) {
        return userMapper.getByUserName(username);
    }

    /**
     * 注册用户
     * @param username
     * @param password
     */
    @Override
    public void addUser(String username, String password) {
        String passwardSS= DigestUtils.md5DigestAsHex(password.getBytes());
        User user1 = User.builder()
                .username(username)
                .password(passwardSS)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.insert(user1);
    }
}
