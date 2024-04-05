package com.bigevent.service;

import com.bigevent.pojo.Category;
import com.bigevent.pojo.dto.CategoryDTO;
import com.bigevent.pojo.dto.CategoryIdDTO;

import java.util.List;

public interface CategoryService {
    /**
     * 文章分类列表
     * @return
     */
    List<Category> categoryList();

    /**
     * 新增文章分类
     * @param categoryDTO
     */
    void addCategory(CategoryDTO categoryDTO);

    /**
     * 更新文章分类
     * @param categoryIdDTO
     */
    void update(CategoryIdDTO categoryIdDTO);
}
