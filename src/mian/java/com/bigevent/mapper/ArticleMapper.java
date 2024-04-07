package com.bigevent.mapper;

import com.bigevent.pojo.Article;
import com.bigevent.pojo.dto.ArticleIdDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleMapper {
    /**
     * 新增文章
     * @param article
     */
    @Insert("insert into article(title, content, cover_img,state, category_id, create_user, create_time, update_time) VALUES " +
            "(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void insert(Article article);

    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg}," +
            "state=#{state},category_id=#{categoryId} ,update_time=now() where id=#{id}")
    void update(ArticleIdDTO articleIdDTO);
}
