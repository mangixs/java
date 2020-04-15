package com.classes.style.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassNotice;

import java.util.List;

public interface ClassNoticeService extends IService<ClassNotice> {
    ClassNotice getSingleNotice(Integer id);

    void editNotice(JSONObject post);

    Page<ClassNotice> getNoticeList(Integer page);

    void deleteNotice(Integer id);

    ClassNotice getNewsNotice();

    List<ClassNotice> getNoticeData();
}
