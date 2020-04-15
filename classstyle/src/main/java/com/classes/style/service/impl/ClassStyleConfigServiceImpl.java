package com.classes.style.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassStyleConfig;
import com.classes.style.mapper.ClassStyleConfigMapper;
import com.classes.style.service.ClassStyleConfigService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassStyleConfigServiceImpl extends ServiceImpl<ClassStyleConfigMapper, ClassStyleConfig>
        implements ClassStyleConfigService {
    @Resource
    private ClassStyleConfigMapper classStyleConfigMapper;

    @Override
    public Map<String, Object> getClassConfigByName() {
        Map<String, Object> res = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(ClassStyleConfigContants.CLASS_SUMMARY_CODE);
        list.add(ClassStyleConfigContants.CLASS_SUMMARY_IMAGE_CODE);
        List<ClassStyleConfig> classStyleConfigList = classStyleConfigMapper.getClassStyleConfigByName(list);
        classStyleConfigList.forEach(x -> {
            res.put(x.getConfigName(), x.getConfigValue());
        });
        return res;
    }

    @Override
    @Transactional
    public void saveClassStyleConfigSummary(JSONObject post) {
        String summary = post.getString("summary");
        ClassStyleConfig classStyleConfig = classStyleConfigMapper
                .getClassConfigByCode(ClassStyleConfigContants.CLASS_SUMMARY_CODE);
        if (classStyleConfig == null) {
            classStyleConfig = new ClassStyleConfig();
            classStyleConfig.setConfigDesc("班级简介");
            classStyleConfig.setConfigName(ClassStyleConfigContants.CLASS_SUMMARY_CODE);
            classStyleConfig.setConfigValue(summary);
            classStyleConfig.setIsValid(1);
            classStyleConfig.setOperator(HttpUtil.getOperator());
            classStyleConfig.setCreatedAt(DateUtil.getTimeNow());
            classStyleConfig.setUpdatedAt(DateUtil.getTimeNow());
            classStyleConfigMapper.insertConfig(classStyleConfig);
        } else {
            classStyleConfig.setConfigValue(summary);
            classStyleConfig.setOperator(HttpUtil.getOperator());
            classStyleConfig.setUpdatedAt(DateUtil.getTimeNow());
            classStyleConfigMapper.updateConfig(classStyleConfig);
        }

        String summaryImage = post.getString("image");

        ClassStyleConfig classStyleConfigImage = classStyleConfigMapper
                .getClassConfigByCode(ClassStyleConfigContants.CLASS_SUMMARY_IMAGE_CODE);
        if (classStyleConfigImage == null) {
            classStyleConfigImage = new ClassStyleConfig();
            classStyleConfigImage.setConfigDesc("班级简介的图片");
            classStyleConfigImage.setConfigName(ClassStyleConfigContants.CLASS_SUMMARY_IMAGE_CODE);
            classStyleConfigImage.setConfigValue(summaryImage);
            classStyleConfigImage.setIsValid(1);
            classStyleConfigImage.setOperator(HttpUtil.getOperator());
            classStyleConfigImage.setCreatedAt(DateUtil.getTimeNow());
            classStyleConfigImage.setUpdatedAt(DateUtil.getTimeNow());
            classStyleConfigMapper.insertConfig(classStyleConfigImage);
        } else {
            classStyleConfigImage.setConfigValue(summaryImage);
            classStyleConfigImage.setOperator(HttpUtil.getOperator());
            classStyleConfigImage.setUpdatedAt(DateUtil.getTimeNow());
            classStyleConfigMapper.updateConfig(classStyleConfigImage);
        }

    }
}
