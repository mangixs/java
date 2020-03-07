package com.example.demo.config;

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
public class TestAspect {
    private Logger log = LoggerFactory.getLogger(TestAspect.class);

    @Pointcut("execution(* com.example.demo.service.UserService.*(..))")
    private void userAspect(){
    }

    @Pointcut("execution(* com.example.demo.service.YbProductStockService.*(..))")
    private void productAspect(){
    }

    @Before("userAspect()")
    public void userAspectBefore(){
        log.info("UserService.action.....");
        writeLog();
    }

    @Before("productAspect()")
    public void productAspectBefore(){
        log.info("YbProductStockService.action....");
        writeLog();
    }

    private void writeLog(){
        log.info("没事我就想写个日志");
    }
}
