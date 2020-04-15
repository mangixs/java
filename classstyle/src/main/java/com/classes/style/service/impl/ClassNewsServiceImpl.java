package com.classes.style.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassNews;
import com.classes.style.mapper.ClassNewsMapper;
import com.classes.style.service.ClassNewsService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassNewsServiceImpl extends ServiceImpl<ClassNewsMapper, ClassNews> implements ClassNewsService {

    @Autowired
    private ClassNewsMapper classNewsMapper;

    @Override
    @Transactional
    public void saveNews(JSONObject post) {
        String action = post.getString("action");
        if ("add".equals(action)) {
            ClassNews classNews = new ClassNews();
            classNews.setAuthor(HttpUtil.getOperator());
            classNews.setContext(post.getString("context"));
            classNews.setTitle(post.getString("title"));
            classNews.setOperator(HttpUtil.getOperator());
            classNews.setCreatedAt(DateUtil.getTimeNow());
            classNews.setUpdatedAt(DateUtil.getTimeNow());
            classNews.setNewType(post.getInteger("choiceType"));
            classNewsMapper.insert(classNews);
        }

        if ("edit".equals(action)) {
            Integer id = post.getInteger("id");
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", id);
            ClassNews classNews = classNewsMapper.selectOne(queryWrapper);
            classNews.setContext(post.getString("context"));
            classNews.setTitle(post.getString("title"));
            classNews.setOperator(HttpUtil.getOperator());
            classNews.setUpdatedAt(DateUtil.getTimeNow());
            classNews.setNewType(post.getInteger("choiceType"));
            classNewsMapper.update(classNews, queryWrapper);
        }
    }

    @Override
    public ClassNews getSingleNews(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.select("id", "title", "context", "new_type", "updated_at");
        ClassNews classNews = classNewsMapper.selectOne(queryWrapper);
        return classNews;
    }

    @Override
    public Page<ClassNews> getNewsList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassNews> res = classNewsMapper.getNewsList(page);
        return page.setRecords(res);
    }

    @Override
    @Transactional
    public void deleteNews(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassNews classNews = classNewsMapper.selectOne(queryWrapper);
        classNews.setIsValid(2);
        classNews.setUpdatedAt(DateUtil.getTimeNow());
        classNewsMapper.update(classNews, queryWrapper);
    }

    @Override
    public Page<ClassNews> getNewsListByType(Integer pageNumber, Integer newsType) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassNews> res = classNewsMapper.getNewsListByType(page, newsType);
        return page.setRecords(res);
    }
}
