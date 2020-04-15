package com.classes.style.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassStyleConfig;

import java.util.Map;

public interface ClassStyleConfigService extends IService<ClassStyleConfig> {
     Map<String, Object> getClassConfigByName();

     void saveClassStyleConfigSummary(JSONObject post);
}
