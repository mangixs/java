package com.classes.style.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassStyleConfig;
import com.classes.style.service.ClassStyleConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/config")
public class ConfigController {
    private static final Logger log = LoggerFactory.getLogger(ClassStyleConfig.class);
    @Resource
    private ClassStyleConfigService classStyleConfigService;

    @GetMapping("/classSummary")
    public ResponseMsg getClassSummary() {
        try {
            Map<String, Object> res = classStyleConfigService.getClassConfigByName();
            return new ResponseMsg(Code.SUCCESS, res, "获取成功");
        } catch (Exception e) {
            log.info("请求 classSummary 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @PostMapping("/saveSummary")
    public ResponseMsg saveSummary(@RequestBody JSONObject data) {
        try {
            classStyleConfigService.saveClassStyleConfigSummary(data);
            return new ResponseMsg(Code.SUCCESS, "", "保存成功");
        } catch (Exception e) {
            log.info("请求 saveSummary 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "保存失败");
        }
    }
}
