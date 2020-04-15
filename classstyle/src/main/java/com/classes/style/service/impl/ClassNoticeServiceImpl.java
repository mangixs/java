package com.classes.style.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassNotice;
import com.classes.style.mapper.ClassNoticeMapper;
import com.classes.style.service.ClassNoticeService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ClassNoticeServiceImpl extends ServiceImpl<ClassNoticeMapper, ClassNotice> implements ClassNoticeService {

    @Autowired
    private ClassNoticeMapper classNoticeMapper;

    @Override
    public ClassNotice getSingleNotice(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        return classNoticeMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public void editNotice(JSONObject post) {
        String action = post.getString("action");
        String title = post.getString("title");
        String context = post.getString("context");
        if ("add".equals(action)) {
            ClassNotice classNotice = new ClassNotice();
            classNotice.setAuthor(HttpUtil.getOperator());
            classNotice.setTitle(title);
            classNotice.setContext(context);
            classNotice.setOperator(HttpUtil.getOperator());
            classNotice.setCreatedAt(DateUtil.getTimeNow());
            classNotice.setUpdatedAt(DateUtil.getTimeNow());
            classNoticeMapper.insert(classNotice);
        }

        if ("edit".equals(action)) {
            Integer id = post.getInteger("id");
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", id);
            ClassNotice classNotice = classNoticeMapper.selectOne(queryWrapper);
            classNotice.setTitle(title);
            classNotice.setContext(context);
            classNotice.setOperator(HttpUtil.getOperator());
            classNotice.setUpdatedAt(DateUtil.getTimeNow());
            classNoticeMapper.update(classNotice, queryWrapper);
        }
    }

    @Override
    public Page<ClassNotice> getNoticeList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassNotice> res = classNoticeMapper.getNoticeList(page);
        return page.setRecords(res);
    }

    @Override
    @Transactional
    public void deleteNotice(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassNotice classNotice = classNoticeMapper.selectOne(queryWrapper);
        classNotice.setIsValid(2);
        classNoticeMapper.update(classNotice, queryWrapper);
    }

    @Override
    public ClassNotice getNewsNotice() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("title", "context");
        queryWrapper.eq("is_valid", 1);
        queryWrapper.last("order by id desc limit 1");
        return classNoticeMapper.selectOne(queryWrapper);
    }

    @Override
    public List<ClassNotice> getNoticeData() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("title", "context");
        queryWrapper.eq("is_valid", 1);
        return classNoticeMapper.selectList(queryWrapper);
    }
}
