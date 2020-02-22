package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class TestNote {
    public static void main(String[] args) {
        mapForeach();
    }

    public static void mapForeach(){
        Map<String, String> res = new HashMap<>();
        res.put("key1", "123");
        res.put("key2", "value2");
        for (Map.Entry<String, String> entry : res.entrySet()) {
            String keys = entry.getKey();
            String values = (String) entry.getValue();
            System.out.println("key--" + keys + "-- values --" + values);
        }
    }


}
