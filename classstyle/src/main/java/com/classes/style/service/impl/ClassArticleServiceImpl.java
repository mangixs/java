package com.classes.style.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassArticle;
import com.classes.style.mapper.ClassArticleMapper;
import com.classes.style.service.ClassArticleService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassArticleServiceImpl extends ServiceImpl<ClassArticleMapper, ClassArticle>
        implements ClassArticleService {

    @Autowired
    private ClassArticleMapper classArticleMapper;

    @Override
    @Transactional
    public void saveArticle(JSONObject post) {
        String action = post.getString("action");
        if ("add".equals(action)) {
            ClassArticle classArticle = new ClassArticle();
            classArticle.setAuthor(HttpUtil.getOperator());
            classArticle.setContext(post.getString("context"));
            classArticle.setTitle(post.getString("title"));
            classArticle.setOperator(HttpUtil.getOperator());
            classArticle.setCreatedAt(DateUtil.getTimeNow());
            classArticle.setUpdatedAt(DateUtil.getTimeNow());
            classArticle.setNewType(post.getInteger("choiceType"));
            classArticleMapper.insert(classArticle);
        }

        if ("edit".equals(action)) {
            Integer id = post.getInteger("id");
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", id);
            ClassArticle classArticle = classArticleMapper.selectOne(queryWrapper);
            classArticle.setContext(post.getString("context"));
            classArticle.setTitle(post.getString("title"));
            classArticle.setOperator(HttpUtil.getOperator());
            classArticle.setUpdatedAt(DateUtil.getTimeNow());
            classArticle.setNewType(post.getInteger("choiceType"));
            classArticleMapper.update(classArticle, queryWrapper);
        }
    }

    @Override
    public ClassArticle getSingleArticle(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.select("id", "title", "context", "new_type", "DATE_FORMAT(updated_at,'%Y-%m-%d') as updated_at");
        ClassArticle classArticle = classArticleMapper.selectOne(queryWrapper);
        return classArticle;
    }

    @Override
    public Page<ClassArticle> getArticleList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassArticle> res = classArticleMapper.getArticleList(page);
        return page.setRecords(res);
    }

    @Override
    @Transactional
    public void deleteArticle(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassArticle classArticle = classArticleMapper.selectOne(queryWrapper);
        classArticle.setIsValid(2);
        classArticle.setUpdatedAt(DateUtil.getTimeNow());
        classArticleMapper.update(classArticle, queryWrapper);
    }

    @Override
    public Map<String, Object> getIndexArticleList() {
        Map<String, Object> res = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "title", "DATE_FORMAT(updated_at,'%Y-%m-%d') as updated_at");
        queryWrapper.eq("is_valid", 1);
        queryWrapper.eq("new_type", 1);
        queryWrapper.last("order by id asc limit 5");
        List<ClassArticle> profession = classArticleMapper.selectList(queryWrapper);
        res.put("profession", profession);
        QueryWrapper otherQueryWrapper = new QueryWrapper();
        otherQueryWrapper.select("id", "title", "DATE_FORMAT(updated_at,'%Y-%m-%d') as updated_at");
        otherQueryWrapper.eq("new_type", 2);
        otherQueryWrapper.eq("is_valid", 1);
        otherQueryWrapper.last("order by id asc limit 5");
        List<ClassArticle> other = classArticleMapper.selectList(otherQueryWrapper);
        res.put("other", other);
        return res;
    }

    @Override
    public Page<ClassArticle> getArticleTypeByPage(Integer pageNumber, Integer articleType) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassArticle> list = classArticleMapper.getArticleTypeByPage(page, articleType);
        return page.setRecords(list);
    }
}
