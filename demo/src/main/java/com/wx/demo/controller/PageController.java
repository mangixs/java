package com.wx.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("page")
    public String index(){
        System.out.println("hello wolrd page");
        return "page/index";
    }
}
