package com.classes.style.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/notice")
    public String notice() {
        return "home/notice";
    }

    @GetMapping("/single")
    public String single() {
        return "home/single";
    }

    @GetMapping("/newList")
    public String newList() {
        return "home/newList";
    }

    @GetMapping("/archive")
    public String archive() {
        return "home/archive";
    }

    @GetMapping("/honor")
    public String honor() {
        return "home/honor";
    }

    @GetMapping("/learningGarden")
    public String learningGarden() {
        return "home/learningGarden";
    }

    @GetMapping("/message")
    public String message() {
        return "home/message";
    }

    @GetMapping("/article")
    public String article() {
        return "home/article";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "admin/sign-up";
    }

    @RequestMapping("/login")
    public String login() {
        return "admin/sign-in";
    }

}
