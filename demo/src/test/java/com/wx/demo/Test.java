package com.wx.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private final static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
//        System.out.println("Hello world");
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String timeStamp = df.format(LocalDateTime.now());
//        System.out.println(timeStamp);
//          Map<String, Object> map = new HashMap<>();
//          map.put("key1", 1);
//          System.out.println(map.toString());
//          int num = (int) map.get("key1");
//          num += 2;
//          map.put("key1", num);
//          System.out.println(map.toString());
//          map.put("key2", "wx");
//          map.forEach((k,v) -> {
//              System.out.println("key---" + k + "---value--" + v);
//          });
        List<String> list = new ArrayList<>();
        list.add("key1");
        list.add("key2");
        list.forEach((str) ->{
            logger.info(str);
        });
        int a = 100;
        int b = 200;

        Integer c = 100;
        Integer d = 200;

        if (a == c) {
            logger.info("1");
        }

        if (b == d) {
            logger.info("2");
        }
    }
}
