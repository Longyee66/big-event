package com.bigevent.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {
    @ApiModelProperty("分类名称")
    private String categoryName;//分类名称
    @ApiModelProperty("分类别名")
    private String categoryAlias;//分类别名
}
