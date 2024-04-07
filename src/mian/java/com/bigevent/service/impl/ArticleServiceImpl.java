package com.bigevent.service.impl;

import com.bigevent.mapper.ArticleMapper;
import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.Article;
import com.bigevent.pojo.PageBean;
import com.bigevent.pojo.User;
import com.bigevent.pojo.dto.ArticleDTO;
import com.bigevent.pojo.dto.ArticleIdDTO;
import com.bigevent.pojo.dto.ArticlePageQueryDTO;
import com.bigevent.service.ArticleService;
import com.bigevent.utils.ThreadLocalUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void addArticle(ArticleDTO articleDTO) {
        Article article=new Article();
        BeanUtils.copyProperties(articleDTO,article);
        article.setCreateUser(getUserID());
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.insert(article);
    }

    @Override
    public void update(ArticleIdDTO articleIdDTO) {
        articleMapper.update(articleIdDTO);
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.getById(id);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public PageBean pageQuery(ArticlePageQueryDTO articlePageQueryDTO) {
        PageHelper.startPage(articlePageQueryDTO.getPageNum(),articlePageQueryDTO.getPageSize());
        //将查询结果进行封装
        Page<Article> page=articleMapper.pageQuery( articlePageQueryDTO);
        return new PageBean(page.getTotal(),page.getResult());
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
