package com.classes.style.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassHonor;

import java.util.List;

public interface ClassHonorService extends IService<ClassHonor> {
    void showEdit(JSONObject post);

    void deleteActivity(Integer id);

    Page<ClassHonor> getHonorList(Integer pageNumber);

    void addHonor(JSONObject post);

    List<ClassHonor> getIndexHonor();
}
