package com.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    private Integer id;//主键ID
    private String categoryName;//分类名称
    private String categoryAlias;//分类别名
    @JsonIgnore
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
