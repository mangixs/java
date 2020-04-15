package com.wx.classstyle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "class_style_config")
public class ClassStyleConfig {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("config_name")
    private String configName;

    @TableField("config_value")
    private String configValue;

    @TableField("config_desc")
    private String configDesc;

    @TableField("is_valid")
    private Integer isValid;

    @TableField("operator")
    private String operator;

    @TableField("created_at")
    private String createdAt;

    @TableField("updated_at")
    private String updatedAt;
}
