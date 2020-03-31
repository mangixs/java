package com.wx.demo.entity;

import lombok.Data;

@Data
public class Menu {
    private int id;
    private String menu_name;
    private String url;
    private String icon;
    private int parent_id;
    private int sort;
    private int func_id;
    private int valid;
    private String created_at;
    private String updated_at;
    private int level;
}
