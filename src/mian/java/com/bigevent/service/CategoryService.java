package com.bigevent.service;

import com.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 文章分类列表
     * @return
     */
    List<Category> categoryList();
}
