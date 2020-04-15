package com.classes.style.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.ClassUser;
import com.classes.style.service.ClassUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ClassUserService classUserService;

    @RequestMapping(value = "/getClassOfficeList", method = RequestMethod.GET)
    public ResponseMsg getClassOfficeList() {
        try {
            List<ClassUser> list = classUserService.getClassOfficeList();
            return new ResponseMsg(Code.SUCCESS, list, "获取成功");
        } catch (Exception e) {
            log.info("请求 getClassOfficeList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @PostMapping("/saveUserSummary")
    public ResponseMsg saveUserSummary(@RequestBody JSONObject post) {
        try {
            classUserService.saveUserSummary(post);
            return new ResponseMsg(Code.SUCCESS, "", "保存简介成功");
        } catch (Exception e) {
            log.info("请求 saveUserSummary 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "保存简介失败");
        }
    }

    @PostMapping("/showEdit")
    public ResponseMsg showEdit(@RequestBody JSONObject post) {
        try {
            classUserService.showEdit(post);
            return new ResponseMsg(Code.SUCCESS, "", "显示编辑成功");
        } catch (Exception e) {
            log.info("请求 showEdit 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "显示编辑失败");
        }
    }

    @PostMapping("/saveUserHeaderImages")
    public ResponseMsg saveUserHeaderImages(@RequestBody JSONObject post) {
        try {
            classUserService.saveUserHeaderImages(post);
            return new ResponseMsg(Code.SUCCESS, "", "上传图片成功");
        } catch (Exception e) {
            log.info("请求 saveUserHeaderImages 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "上传图片失败");
        }
    }

    @GetMapping("/getUserList")
    public ResponseMsg getUserList() {
        try {
            List<ClassUser> res = classUserService.getUserList();
            return new ResponseMsg(Code.SUCCESS, res, "获取数据成功");
        } catch (Exception e) {
            log.info("请求 getUserList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取数据失败");
        }
    }

    @PostMapping("/editUserAccountStatus")
    public ResponseMsg editUserAccountStatus(@RequestBody JSONObject post) {
        try {
            classUserService.editUserAccountStatus(post);
            return new ResponseMsg(Code.SUCCESS, "", "编辑状态成功");
        } catch (Exception e) {
            log.info("请求 editUserAccountStatus 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "编辑状态失败");
        }
    }

    @PostMapping("/saveOfficeName")
    public ResponseMsg saveOfficeName(@RequestBody JSONObject post) {
        try {
            classUserService.saveOfficeName(post);
            return new ResponseMsg(Code.SUCCESS, "", "编辑职位成功");
        } catch (Exception e) {
            log.info("请求 saveOfficeName 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "编辑职位失败");
        }
    }

    @GetMapping("/getUserSession")
    public ResponseMsg getUserSession(HttpSession session) {
        try {
            Map<String, Object> res = new HashMap<>();
            res.put("username", session.getAttribute("username"));
            res.put("userType", session.getAttribute("userType"));
            return new ResponseMsg(Code.SUCCESS, res, "获取用户信息成功");
        } catch (Exception e) {
            log.info("请求 getUserSession 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取用户session失败");
        }
    }
}
