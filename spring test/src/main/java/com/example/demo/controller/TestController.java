package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.WinitApi;
import com.example.demo.config.WinitConfig;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private WinitConfig winitConfig;



    @RequestMapping("/winit")
    public String winitApi(){
        WinitApi api = winitConfig.getWinitApi();
        api.setConfig("api");
        JSONObject res = api.queryOutboundOrder("WO2369140545");
        return res.toJSONString();
    }

    public void TestOne(){
        logger.info("logger---->one");
    }
}
