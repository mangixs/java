package com.wx.classstyle.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-100)
public class AdminAspect {
    private Logger log = LoggerFactory.getLogger(AdminAspect.class);

    @Pointcut("execution(* com.wx.classstyle.controller.admin.*(..))")
    private void adminLoginAspect(){

    }

    @Before("adminLoginAspect()")
    public void adminLoginAspectBefore(){
        checkUserIsLogin();
    }

    public void checkUserIsLogin(){

    }
}
