package com.classes.style.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.ClassHonor;
import com.classes.style.service.ClassHonorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/honor")
public class HonorController {
    private static final Logger log = LoggerFactory.getLogger(HonorController.class);

    @Autowired
    private ClassHonorService classHonorService;

    @PostMapping("/showEdit")
    public ResponseMsg showEdit(@RequestBody JSONObject post) {
        try {
            classHonorService.showEdit(post);
            return new ResponseMsg(Code.SUCCESS, "", "获奖信息显示编辑成功");
        } catch (Exception e) {
            log.info("请求 showEdit 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获奖信息显示编辑失败");
        }
    }

    @GetMapping("/deleteHonor/{id}")
    public ResponseMsg deleteHonor(@PathVariable Integer id) {
        try {
            classHonorService.deleteActivity(id);
            return new ResponseMsg(Code.SUCCESS, "", "删除获奖信息成功");
        } catch (Exception e) {
            log.info("请求 deleteHonor 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "删除获奖信息失败");
        }
    }

    @GetMapping("/getHonorList/{pageNumber}")
    public ResponseMsg getHonorList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassHonor> list = classHonorService.getHonorList(pageNumber);
            return new ResponseMsg(Code.SUCCESS, list, "请求获奖信息数据成功");
        } catch (Exception e) {
            log.info("请求 getHonorList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "请求获奖信息数据失败");
        }
    }

    @PostMapping("/addHonor")
    public ResponseMsg addHonor(@RequestBody JSONObject post) {
        try {
            classHonorService.addHonor(post);
            return new ResponseMsg(Code.SUCCESS, "", "删除获奖信息成功");
        } catch (Exception e) {
            log.info("请求 addHonor 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "删除获奖信息失败");
        }
    }
}
