package com.wx.classstyle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController{
    private Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }

    @ResponseBody
    @PostMapping("/loginSub")
    public void loginSub(HttpSession session, HttpServletResponse response, String username, String password) throws IOException {
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        response.sendRedirect("/admin");
    }
}
