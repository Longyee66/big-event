package com.bigevent.service;

import com.bigevent.pojo.Article;
import com.bigevent.pojo.dto.ArticleDTO;
import com.bigevent.pojo.dto.ArticleIdDTO;

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
}
