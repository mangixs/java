package com.classes.style.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@TableName("class_news")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassNews {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("context")
    private String context;

    @TableField("new_type")
    private Integer newType;

    @TableField("author")
    private String author;

    @TableField("is_valid")
    private Integer isValid;

    @TableField("operator")
    private String operator;

    @TableField("created_at")
    private String createdAt;

    @TableField("updated_at")
    private String updatedAt;
}
