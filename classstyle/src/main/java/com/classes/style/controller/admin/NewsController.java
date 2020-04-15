package com.classes.style.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.ClassNews;
import com.classes.style.service.ClassNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/news")
public class NewsController {
    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private ClassNewsService classNewsService;

    @RequestMapping(value = "/saveNews", method = RequestMethod.POST)
    public ResponseMsg saveNews(@RequestBody JSONObject post) {
        try {
            classNewsService.saveNews(post);
            return new ResponseMsg(Code.SUCCESS, "", "编辑新闻成功");
        } catch (Exception e) {
            log.info("请求 saveNews 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "添加新闻失败");
        }
    }

    @GetMapping("/getSingleNews/{id}")
    public ResponseMsg getSingleNews(@PathVariable Integer id) {
        try {
            ClassNews classNews = classNewsService.getSingleNews(id);
            return new ResponseMsg(Code.SUCCESS, classNews, "获取成功");
        } catch (Exception e) {
            log.info("请求 getSingleNews 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/getNewsList/{pageNumebr}")
    public ResponseMsg getNewsList(@PathVariable Integer pageNumebr) {
        try {
            Page<ClassNews> list = classNewsService.getNewsList(pageNumebr);
            return new ResponseMsg(Code.SUCCESS, list, "获取成功");
        } catch (Exception e) {
            log.info("请求 getNewsList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/deleteNews/{id}")
    public ResponseMsg deleteNews(@PathVariable Integer id) {
        try {
            classNewsService.deleteNews(id);
            return new ResponseMsg(Code.SUCCESS, "", "删除成功");
        } catch (Exception e) {
            log.info("请求 deleteNews 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "删除失败");
        }
    }
}
