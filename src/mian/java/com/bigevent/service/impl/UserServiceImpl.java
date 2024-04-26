package com.bigevent.service.impl;

import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.User;
import com.bigevent.pojo.dto.UserDTO;
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

    /**
     * 更新用户基本信息
     * @param user
     */
    @Override
    public void update(UserDTO user) {
        userMapper.update(user);
    }

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    @Override
    public void updateAvarter(String avatarUrl) {
        User user=getUser();
        userMapper.updateAvarter(avatarUrl,user.getId());
    }
    /**
     * 更新用户密码
     * @param password
     */
    @Override
    public void updatePwd(String password) {
        User user=getUser();
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        userMapper.updatePwd(passwordMd5,user.getId());
    }
    /**
     * 公共获取用户信息方法
     * @return
     */
    private User getUser(){
        Map map=new HashMap<>();
        map.put("username", ThreadLocalUtils.getCurrentUserName());
        User user=userMapper.getByUser(map);
        return user;
    }
}
