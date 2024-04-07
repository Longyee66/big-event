package com.bigevent.service;

import com.bigevent.pojo.Article;
import com.bigevent.pojo.PageBean;
import com.bigevent.pojo.dto.ArticleDTO;
import com.bigevent.pojo.dto.ArticleIdDTO;
import com.bigevent.pojo.dto.ArticlePageQueryDTO;

import java.util.Map;

public interface ArticleService {
    /**
     *  发布文章
     * @param articleDTO
     */
    void addArticle(ArticleDTO articleDTO);

    /**
     * 更新文章
     * @param articleIdDTO
     */
    void update(ArticleIdDTO articleIdDTO);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    Article findById(Integer id);

    /**
     * 删除文章
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页查询
     * @param pageMap
     * @return
     */
    PageBean pageQuery(ArticlePageQueryDTO articlePageQueryDTO);
}
