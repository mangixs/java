package com.classes.style.controller.admin;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.ClassActivity;
import com.classes.style.service.ClassActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/admin/activity")
public class ActivityController {
    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ClassActivityService classActivityService;

    @PostMapping(value = "/addActivity")
    public ResponseMsg addActivity(@RequestBody JSONObject post) {
        try {
            classActivityService.addActivity(post);
            return new ResponseMsg(Code.SUCCESS, "", "添加活动成功");
        } catch (Exception e) {
            log.info("请求 addActivity 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "添加活动失败");
        }
    }

    @GetMapping("/getActivityList/{pageNumber}")
    public ResponseMsg getActivityList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassActivity> list = classActivityService.getActivityList(pageNumber);
            return new ResponseMsg(Code.SUCCESS, list, "请求活动数据成功");
        } catch (Exception e) {
            log.info("请求 getActivityList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "请求活动数据失败");
        }
    }

    @GetMapping("/deleteActivity/{id}")
    public ResponseMsg deleteActivity(@PathVariable Integer id) {
        try {
            classActivityService.deleteActivity(id);
            return new ResponseMsg(Code.SUCCESS, "", "删除活动成功");
        } catch (Exception e) {
            log.info("请求 deleteActivity 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "删除活动失败");
        }
    }

    @PostMapping("/showEdit")
    public ResponseMsg showEdit(@RequestBody JSONObject post) {
        try {
            classActivityService.showEdit(post);
            return new ResponseMsg(Code.SUCCESS, "", "活动显示编辑成功");
        } catch (Exception e) {
            log.info("请求 showEdit 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "活动显示编辑失败");
        }
    }

    @PostMapping("/saveActivityImages")
    public ResponseMsg saveActivityImages(@RequestBody JSONObject post) {
        try {
            classActivityService.saveActivityImages(post);
            return new ResponseMsg(Code.SUCCESS, "", "活动图片保存成功");
        } catch (Exception e) {
            log.info("请求 saveActivityImages 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "活动图片保存失败");
        }
    }

    @PostMapping("/saveActivityProgram")
    public ResponseMsg saveActivityProgram(@RequestBody JSONObject post) {
        try {
            classActivityService.saveActivityProgram(post);
            return new ResponseMsg(Code.SUCCESS, "", "活动方案保存成功");
        } catch (Exception e) {
            log.info("请求 saveActivityProgram 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "活动方案保存失败");
        }
    }

    @RequestMapping(value = "/downloadFile/{id}")
    public HttpServletResponse downloadFile(HttpServletResponse response, @PathVariable Integer id) {
        ClassActivity classActivity = classActivityService.getById(id);
        String downloadFilePath = classActivity.getProgram();// 被下载的文件在服务器中的路径,
        File file = new File(downloadFilePath);
        String fileName = "";// 被下载文件的名称
        try {
            fileName = new String(file.getName().getBytes("UTF-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @GetMapping("/getPlanActivityList/{pageNumber}")
    public ResponseMsg getPlanActivityList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassActivity> list = classActivityService.getPlanActivityList(pageNumber);
            return new ResponseMsg(Code.SUCCESS, list, "请求活动数据成功");
        } catch (Exception e) {
            log.info("请求 getPlanActivityList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "请求活动数据失败");
        }
    }

    @PostMapping("/auditActivity")
    public ResponseMsg auditActivity(@RequestBody JSONObject post) {
        try {
            classActivityService.auditActivity(post);
            return new ResponseMsg(Code.SUCCESS, "", "操作成功");
        } catch (Exception e) {
            log.info("请求 auditActivity 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "操作失败");
        }
    }
}
