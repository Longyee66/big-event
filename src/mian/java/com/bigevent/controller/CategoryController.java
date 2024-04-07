package com.bigevent.controller;

import com.bigevent.pojo.Category;
import com.bigevent.pojo.Result;
import com.bigevent.pojo.dto.CategoryDTO;
import com.bigevent.pojo.dto.CategoryIdDTO;
import com.bigevent.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
    @PostMapping
    @ApiOperation("新增文章分类")
    public Result addCategory(@RequestBody @Validated CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }
    @PutMapping
    @ApiOperation("更新文章分类")
    public Result updateCategory(@RequestBody @Validated CategoryIdDTO categoryIdDTO){
        categoryService.update(categoryIdDTO);
        return Result.success();
    }
    @GetMapping("/detail")
    @ApiOperation("获取文章分类详情")
    public Result getById(@NotNull Integer id){
        Category category=categoryService.getByIDcategory(id);
        return Result.success(category);
    }
}
