package com.classes.style.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassArticle;

import java.util.Map;

public interface ClassArticleService extends IService<ClassArticle> {
    void saveArticle(JSONObject post);

    ClassArticle getSingleArticle(Integer id);

    Page<ClassArticle> getArticleList(Integer pageNumber);

    void deleteArticle(Integer id);

    Map<String, Object> getIndexArticleList();

    Page<ClassArticle> getArticleTypeByPage(Integer pageNumber, Integer articleType);
}
