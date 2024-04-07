package com.bigevent.pojo.dto;

import com.bigevent.annotation.State;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ArticleDTO {
    @NotNull
    private Integer categoryId;//文章分类id
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;//文章标题
    @NotEmpty
    private String content;//文章内容
    @NotEmpty
    @URL
    private String coverImg;//封面图像
    @NotEmpty
    @State
    private String state;//发布状态 已发布|草稿

}
