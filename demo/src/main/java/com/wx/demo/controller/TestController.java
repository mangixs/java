package com.wx.demo.controller;

import com.wx.demo.entity.Menu;
import com.wx.demo.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "index", produces ="application/json;charset=utf-8")
    public String index(){
        return "Hello world";
    }

    @RequestMapping(value = "getAllMenu", produces = "application/json; charset=utf-8")
    public List<Menu> getAllMenu(){
        List<Menu> ret = menuService.getAllMenu();
        return ret;
    }
    @GetMapping("/getPrarms")
    @ResponseBody
    // http://www.a.com/getParams?intval=1&str=abc
    public void getParams(Integer intval, String str){
        log.info(String.valueOf(intval));
        log.info(str);
    }

    @GetMapping("/getRequstBody")
    @ResponseBody
    // http://www.a.com/getRequestBody?id=1&str=abc 这里post也可以
    public void getRequstBody(@RequestParam("id") int id, @RequestParam("str") String str){
        log.info(String.valueOf(id));
        log.info(str);
    }

    @GetMapping("/getParamsJson")
    @ResponseBody
    // 获取json参数
    public void getParamsJson(@RequestBody Menu menu){

    }

    @GetMapping("/getPraramByUrl/{id}")
    @ResponseBody
    // http://www.a.com/getPraramByUrl/3
    public void getParamByUrl(@PathVariable("id") int id){
        log.info(String.valueOf(id));
    }
}
