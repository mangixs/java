package com.wx.classstyle.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String index()
    {
        return "home/index";
    }

    @GetMapping("/other")
    public String other()
    {
        return "home/other";
    }
}
