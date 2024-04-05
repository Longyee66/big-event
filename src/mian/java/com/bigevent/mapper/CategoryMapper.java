package com.bigevent.mapper;

import com.bigevent.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 查询文章列表
     * @return
     */
    List<Category> getCategoryList(Integer userId);
}
