package com.classes.style.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassUser;

import java.util.List;
import java.util.Map;

public interface ClassUserService extends IService<ClassUser> {
    Map<String, Object> userRegister(JSONObject post);

    List<ClassUser> getClassOfficeList();

    void saveUserSummary(JSONObject post);

    void showEdit(JSONObject post);

    void saveUserHeaderImages(JSONObject post);

    List<ClassUser> getUserList();

    void editUserAccountStatus(JSONObject post);

    void saveOfficeName(JSONObject post);

    Map<String, Object> login(JSONObject post);

    Map<String, Object> homeLogin(Integer number, String password);
}
