package com.bigevent.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticlePageQueryDTO {
    @NotNull
    private Integer pageNum;//当前页码
    @NotNull
    private Integer pageSize;//每页条数
    private Integer categoryId;//文章分类ID
    private String state;//发布状态
    @JsonProperty("userId")
    @JsonIgnore
    private Integer userId;
}
