package com.classes.style.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassActivity;
import com.classes.style.mapper.ClassActivityMapper;
import com.classes.style.service.ClassActivityService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassActivityServiceImpl extends ServiceImpl<ClassActivityMapper, ClassActivity>
        implements ClassActivityService {

    @Autowired
    private ClassActivityMapper classActivityMapper;

    @Override
    public void addActivity(JSONObject post) {
        ClassActivity classActivity = new ClassActivity();
        classActivity.setTitle(post.getString("title"));
        classActivity.setProgram("");
        classActivity.setActivityImg("");
        classActivity.setAddUser(HttpUtil.getOperator());
        classActivity.setOperator(HttpUtil.getOperator());
        classActivity.setCreatedAt(DateUtil.getTimeNow());
        classActivity.setUpdatedAt(DateUtil.getTimeNow());
        classActivityMapper.insert(classActivity);
    }

    @Override
    public Page<ClassActivity> getActivityList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassActivity> res = classActivityMapper.getActivityList(page);
        return page.setRecords(res);
    }

    @Override
    @Transactional
    public void deleteActivity(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassActivity classActivity = classActivityMapper.selectOne(queryWrapper);
        classActivity.setIsValid(2);
        classActivity.setUpdatedAt(DateUtil.getTimeNow());
        classActivityMapper.update(classActivity, queryWrapper);
    }

    @Override
    @Transactional
    public void showEdit(JSONObject post) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", post.getInteger("id"));
        ClassActivity classActivity = classActivityMapper.selectOne(queryWrapper);
        classActivity.setIsShow(post.getInteger("show"));
        classActivity.setUpdatedAt(DateUtil.getTimeNow());
        classActivityMapper.update(classActivity, queryWrapper);
    }

    @Override
    @Transactional
    public void saveActivityImages(JSONObject post) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", post.getInteger("id"));
        ClassActivity classActivity = classActivityMapper.selectOne(queryWrapper);
        classActivity.setActivityImg(post.getString("img"));
        classActivity.setUpdatedAt(DateUtil.getTimeNow());
        classActivityMapper.update(classActivity, queryWrapper);
    }

    @Override
    public void saveActivityProgram(JSONObject post) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", post.getInteger("id"));
        ClassActivity classActivity = classActivityMapper.selectOne(queryWrapper);
        classActivity.setProgram(post.getString("program"));
        classActivity.setAudit(2);
        classActivity.setUpdatedAt(DateUtil.getTimeNow());
        classActivityMapper.update(classActivity, queryWrapper);
    }

    @Override
    public Page<ClassActivity> getPlanActivityList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassActivity> res = classActivityMapper.getPlanActivityList(page);
        return page.setRecords(res);
    }

    @Override
    @Transactional
    public void auditActivity(JSONObject post) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", post.getInteger("id"));
        ClassActivity classActivity = classActivityMapper.selectOne(queryWrapper);
        classActivity.setOperator(HttpUtil.getOperator());
        classActivity.setAudit(post.getInteger("audit"));
        classActivity.setUpdatedAt(DateUtil.getTimeNow());
        classActivityMapper.update(classActivity, queryWrapper);
    }

    @Override
    public Page<ClassActivity> getArchiveList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassActivity> res = classActivityMapper.getArchiveList(page);
        return page.setRecords(res);
    }
}
