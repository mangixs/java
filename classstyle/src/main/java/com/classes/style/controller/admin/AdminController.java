package com.classes.style.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller()
@RequestMapping(value = "/admin")
public class AdminController {
    private Logger log = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/index")
    public String admin() {
        return "admin/index";
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session, SessionStatus sessionStatus) {
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @RequestMapping("/activity")
    public String activity() {
        return "admin/activity";
    }

    @RequestMapping("/articleDetail")
    public String articleDetail() {
        return "admin/article-detail";
    }

    @RequestMapping("/editArticle")
    public String editArticle() {
        return "admin/edit-article";
    }

    @RequestMapping("/editNew")
    public String editNew() {
        return "admin/edit-new";
    }

    @RequestMapping("/honor")
    public String honor() {
        return "admin/honor";
    }

    @RequestMapping("/learningGarden")
    public String learningGarden() {
        return "admin/learning-garden";
    }

    @RequestMapping("/message")
    public String message() {
        return "admin/message";
    }

    @RequestMapping("/news")
    public String news() {
        return "admin/news";
    }

    @RequestMapping("/newDetail")
    public String newDetail() {
        return "admin/new-detail";
    }

    @RequestMapping("/notice")
    public String notice() {
        return "admin/notice";
    }

    @RequestMapping("/noticeDetail")
    public String noticeDetail() {
        return "admin/notice-detail";
    }

    @RequestMapping("/plan")
    public String plan() {
        return "admin/plan";
    }

    @RequestMapping("/signIn")
    public String signIn() {
        return "admin/sign-in";
    }

    @RequestMapping("/userlist")
    public String userlist() {
        return "admin/userlist";
    }

}
