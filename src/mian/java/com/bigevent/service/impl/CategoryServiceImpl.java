package com.bigevent.service.impl;

import com.bigevent.mapper.CategoryMapper;
import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.Category;
import com.bigevent.pojo.User;
import com.bigevent.pojo.dto.CategoryDTO;
import com.bigevent.pojo.dto.CategoryIdDTO;
import com.bigevent.service.CategoryService;
import com.bigevent.utils.ThreadLocalUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    //分类列表
    @Override
    public List<Category> categoryList() {
        Integer userID=getUserID();
        return categoryMapper.getCategoryList(userID);
    }
    //新增文章分类
    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setCreateUser(getUserID());
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insert(category);
    }

    @Override
    public void update(CategoryIdDTO categoryIdDTO) {
        Category category =new Category();
        BeanUtils.copyProperties(categoryIdDTO,category);
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    /**
     * 公共获取用户信息方法
     * @return
     */
    private Integer getUserID(){
        Map map=new HashMap<>();
        map.put("username", ThreadLocalUtils.getCurrentUserName());
        User user=userMapper.getByUser(map);
        return user.getId();
    }
}
