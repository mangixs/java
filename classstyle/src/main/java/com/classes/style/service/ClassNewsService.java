package com.classes.style.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassNews;

public interface ClassNewsService extends IService<ClassNews> {
    void saveNews(JSONObject post);

    ClassNews getSingleNews(Integer id);

    Page<ClassNews> getNewsList(Integer pageNumber);

    void deleteNews(Integer id);

    Page<ClassNews> getNewsListByType(Integer pageNumber, Integer newsType);
}
