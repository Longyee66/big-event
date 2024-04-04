package com.bigevent.service.impl;

import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.User;
import com.bigevent.service.UserService;
import com.bigevent.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户
     *
     * @param map
     * @return
     */
    @Override
    public User getByUser(Map map) {
        return userMapper.getByUser(map);
    }

    /**
     * 注册用户
     *
     * @param username
     * @param password
     */
    @Override
    public void addUser(String username, String password) {
        String passwardSS = DigestUtils.md5DigestAsHex(password.getBytes());
        User user1 = User.builder()
                .username(username)
                .password(passwardSS)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.insert(user1);
    }

    /**
     * 用户登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        Map map = new HashMap<>();
        map.put("username", username);
        map.put("password", passwordMD5);
        return userMapper.getByUser(map);
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @Override
    public User getUserInfo(String username) {
        Map map = new HashMap<>();
        map.put("username", username);
        /*User user=userMapper.getByUser(map);
        user.setPassword("******");*/
        return userMapper.getByUser(map);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvarter(String avatarUrl) {
        Map map=new HashMap<>();
        map.put("username", ThreadLocalUtils.getCurrentUserName());
        User user=userMapper.getByUser(map);

        userMapper.updateAvarter(avatarUrl,user.getId());
    }
}
