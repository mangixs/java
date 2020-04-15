package com.wx.classstyle.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Aspect
@Order(-100)
public class AdminAspect {
    private Logger log = LoggerFactory.getLogger(AdminAspect.class);

    @Pointcut("execution(* com.wx.classstyle.controller.admin.*.*(..))")
    private void adminLoginAspect(){

    }

    @Before("adminLoginAspect()")
    public void adminLoginAspectBefore() throws IOException {
        //checkUserIsLogin();
    }

    public void checkUserIsLogin() throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        HttpSession session= request.getSession();
        if(session.getAttribute("username") == null) {
            System.out.println("你还没有登陆");
            response.sendRedirect("/login");
        } else {
            System.out.println("你已经登陆了");
        }
    }
}
