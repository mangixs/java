package com.classes.style.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassActivity;

public interface ClassActivityService extends IService<ClassActivity> {
    void addActivity(JSONObject post);

    Page<ClassActivity> getActivityList(Integer pageNumber);

    void deleteActivity(Integer id);

    void showEdit(JSONObject post);

    void saveActivityImages(JSONObject post);

    void saveActivityProgram(JSONObject post);

    Page<ClassActivity> getPlanActivityList(Integer pageNumber);

    void auditActivity(JSONObject post);

    Page<ClassActivity> getArchiveList(Integer pageNumber);
}
