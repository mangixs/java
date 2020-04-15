package com.classes.style.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@TableName("class_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassUser {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("identity")
    private Integer identity;

    @TableField("office")
    private String office;

    @TableField("header_img")
    private String headerImg;

    @TableField("is_show")
    private Integer isShow;

    @TableField("summary")
    private String summary;

    @TableField("number")
    private Integer number;

    @TableField("account_status")
    private Integer accountStatus;

    @TableField("operator")
    private String operator;

    @TableField("created_at")
    private String createdAt;

    @TableField("updated_at")
    private String updatedAt;

    @TableField("user_type")
    private Integer userType;

}
