package com.classes.style.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassHonor;
import com.classes.style.mapper.ClassHonorMapper;
import com.classes.style.service.ClassHonorService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassHonorServiceImpl extends ServiceImpl<ClassHonorMapper, ClassHonor> implements ClassHonorService {

    @Autowired
    private ClassHonorMapper classHonorMapper;

    @Override
    @Transactional
    public void showEdit(JSONObject post) {
        ClassHonor classHonor = classHonorMapper.selectById(post.getInteger("id"));
        classHonor.setIsShow(post.getInteger("show"));
        classHonor.setUpdatedAt(DateUtil.getTimeNow());
        classHonor.setOperator(HttpUtil.getOperator());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", post.getInteger("id"));
        classHonorMapper.update(classHonor, queryWrapper);
    }

    @Override
    @Transactional
    public void deleteActivity(Integer id) {
        ClassHonor classHonor = classHonorMapper.selectById(id);
        classHonor.setIsValid(2);
        classHonor.setUpdatedAt(DateUtil.getTimeNow());
        classHonor.setOperator(HttpUtil.getOperator());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        classHonorMapper.update(classHonor, queryWrapper);
    }

    @Override
    public Page<ClassHonor> getHonorList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassHonor> res = classHonorMapper.getHonorList(page);
        return page.setRecords(res);
    }

    @Override
    public void addHonor(JSONObject post) {
        ClassHonor classHonor = new ClassHonor();
        classHonor.setOperator(HttpUtil.getOperator());
        classHonor.setTitle(post.getString("title"));
        classHonor.setUrl(post.getString("url"));
        classHonor.setOperator(HttpUtil.getOperator());
        classHonor.setCreatedAt(DateUtil.getTimeNow());
        classHonor.setUpdatedAt(DateUtil.getTimeNow());
        classHonorMapper.insert(classHonor);
    }

    @Override
    public List<ClassHonor> getIndexHonor() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "url");
        queryWrapper.eq("is_valid", 1);
        queryWrapper.last("order by id desc limit 4");
        return classHonorMapper.selectList(queryWrapper);
    }
}
