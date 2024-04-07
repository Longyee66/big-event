package com.bigevent.mapper;

import com.bigevent.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 查询文章列表
     * @return
     */
    List<Category> getCategoryList(Integer userId);
    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)" +
            " VALUES (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void insert(Category category);

    /**
     * 更新文章数据
     * @param category
     */
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime}  where id =#{id}")
    void update(Category category);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Select("select id,category_name,category_alias,create_time,update_time from category where id=#{id}")
    Category getByCategoryId(Integer id);
}
