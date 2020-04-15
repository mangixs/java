package com.classes.style.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.ClassArticle;
import com.classes.style.service.ClassArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/article")
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ClassArticleService classArticleService;

    @RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
    public ResponseMsg saveArticle(@RequestBody JSONObject post) {
        try {
            classArticleService.saveArticle(post);
            return new ResponseMsg(Code.SUCCESS, "", "编辑文章成功");
        } catch (Exception e) {
            log.info("请求 saveArticle 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "添加文章失败");
        }
    }

    @GetMapping("/getSingleArticle/{id}")
    public ResponseMsg getSingleArticle(@PathVariable Integer id) {
        try {
            ClassArticle classArticle = classArticleService.getSingleArticle(id);
            return new ResponseMsg(Code.SUCCESS, classArticle, "获取成功");
        } catch (Exception e) {
            log.info("请求 getSingleArticle 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/getArticleList/{pageNumebr}")
    public ResponseMsg getArticleList(@PathVariable Integer pageNumebr) {
        try {
            Page<ClassArticle> list = classArticleService.getArticleList(pageNumebr);
            return new ResponseMsg(Code.SUCCESS, list, "获取成功");
        } catch (Exception e) {
            log.info("请求 getArticleList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/deleteArticle/{id}")
    public ResponseMsg deleteArticle(@PathVariable Integer id) {
        try {
            classArticleService.deleteArticle(id);
            return new ResponseMsg(Code.SUCCESS, "", "删除成功");
        } catch (Exception e) {
            log.info("请求 deleteArticle 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "删除失败");
        }
    }
}
