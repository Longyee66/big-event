package com.bigevent.controller;

import com.bigevent.pojo.Category;
import com.bigevent.pojo.Result;
import com.bigevent.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/category")
@Api(tags = "文章分类相关接口")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    @ApiOperation("文章分类列表")
    public Result<List<Category>> categoryList(){
        List<Category> categoryList=categoryService.categoryList();
        return Result.success(categoryList);
    }

}
