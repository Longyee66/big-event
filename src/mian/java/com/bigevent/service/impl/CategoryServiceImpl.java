package com.bigevent.service.impl;

import com.bigevent.mapper.CategoryMapper;
import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.Category;
import com.bigevent.pojo.User;
import com.bigevent.service.CategoryService;
import com.bigevent.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Category> categoryList() {
        User user=getUser();
        return categoryMapper.getCategoryList(user.getId());
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
