package com.classes.style.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @Value("${spring.server.error404}")
    private String error404;

    @Value("spring.server.error500")
    private String error500;

    @GetMapping(value = "/404")
    public String error404() {
        return error404;
    }

    @GetMapping(value = "/500")
    public String error500() {
        return error500;
    }
}
