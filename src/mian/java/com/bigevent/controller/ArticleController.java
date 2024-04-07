package com.bigevent.controller;

import com.bigevent.pojo.Result;
import com.bigevent.pojo.dto.ArticleDTO;
import com.bigevent.pojo.dto.ArticleIdDTO;
import com.bigevent.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/article")
@Slf4j
@Api(tags = "文章管理相关接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    @ApiOperation("发布文章")
    public Result addArticle(@RequestBody @Validated ArticleDTO articleDTO){
        articleService.addArticle(articleDTO);
        return Result.success();
    }
    @PutMapping
    @ApiOperation("更新文章")
    public Result updateArticle(@RequestBody ArticleIdDTO articleIdDTO){
        articleService.update(articleIdDTO);
        return Result.success();
    }

}
