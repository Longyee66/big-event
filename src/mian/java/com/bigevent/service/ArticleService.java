package com.bigevent.service;

import com.bigevent.pojo.dto.ArticleDTO;

public interface ArticleService {
    /**
     *  发布文章
     * @param articleDTO
     */
    void addArticle(ArticleDTO articleDTO);
}
