package com.classes.style.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.ClassHonor;
import com.classes.style.entity.ClassMessage;
import com.classes.style.service.ClassMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/message")
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private ClassMessageService classMessageService;

    @GetMapping("/deleteMessage/{id}")
    public ResponseMsg deleteMessage(@PathVariable Integer id) {
        try {
            classMessageService.deleteMessage(id);
            return new ResponseMsg(Code.SUCCESS, "", "删除留言成功");
        } catch (Exception e) {
            log.info("请求 deleteHonor 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "删除留言失败");
        }
    }

    @GetMapping("/getMessageList/{pageNumber}")
    public ResponseMsg getMessageList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassMessage> list = classMessageService.getMessageList(pageNumber);
            return new ResponseMsg(Code.SUCCESS, list, "请求获奖信息数据成功");
        } catch (Exception e) {
            log.info("请求 getMessageList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "请求获奖信息数据失败");
        }
    }
}
