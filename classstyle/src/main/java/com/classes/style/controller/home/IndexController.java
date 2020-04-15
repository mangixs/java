package com.wx.classstyle.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @GetMapping("/index")
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
}
