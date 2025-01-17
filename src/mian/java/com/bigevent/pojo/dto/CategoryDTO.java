package com.bigevent.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryDTO {
    @ApiModelProperty("分类名称")
    @NotEmpty
    private String categoryName;//分类名称
    @ApiModelProperty("分类别名")
    @NotEmpty
    private String categoryAlias;//分类别名
}
