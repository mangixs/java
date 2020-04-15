package com.classes.style.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.ClassNotice;
import com.classes.style.service.ClassNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/notice")
public class NoticeController {
    private static final Logger log = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private ClassNoticeService classNoticeService;

    @GetMapping("/getSingleNotice/{id}")
    public ResponseMsg getSingleNotice(@PathVariable Integer id) {
        try {
            ClassNotice classNotice = classNoticeService.getSingleNotice(id);
            return new ResponseMsg(Code.SUCCESS, classNotice, "获取成功");
        } catch (Exception e) {
            log.info("请求 getSingleNotice 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @RequestMapping(value = "/editNotice", method = RequestMethod.POST)
    public ResponseMsg editNotice(@RequestBody JSONObject post) {
        try {
            classNoticeService.editNotice(post);
            return new ResponseMsg(Code.SUCCESS, "", "编辑成功");
        } catch (Exception e) {
            log.info("请求 getSingleNotice 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "编辑失败");
        }
    }

    @GetMapping("/getNoticeList/{page}")
    public ResponseMsg getNoticeList(@PathVariable Integer page) {
        try {
            Page<ClassNotice> list = classNoticeService.getNoticeList(page);
            return new ResponseMsg(Code.SUCCESS, list, "获取成功");
        } catch (Exception e) {
            log.info("请求 getNoticeList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/deleteNotice/{id}")
    public ResponseMsg deleteNotice(@PathVariable Integer id) {
        try {
            classNoticeService.deleteNotice(id);
            return new ResponseMsg(Code.SUCCESS, "", "删除成功");
        } catch (Exception e) {
            log.info("请求 deleteNotice 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "删除失败");
        }
    }
}
