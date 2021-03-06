package com.wx.demo;

import com.wx.demo.entity.mongo.YbWangjiToken;
import com.wx.demo.mongo.YbWangjiTokenMongoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private YbWangjiTokenMongoDao ybWangjiTokenMongoDao;

    @Test
    public void contextLoads() {
    }
    @Test
    public void testOne(){
        System.out.print("测试的一个方法 ---->");
        stringRedisTemplate.opsForValue().set("test-string-value", "Hello Redis");
    }

    @Test
    public void testTwo(){
        String value = stringRedisTemplate.opsForValue().get("test-string-value");
        System.out.println(value);
    }

    @Test
    public void mognodb(){
        YbWangjiToken res = ybWangjiTokenMongoDao.getWangjiAccessToken("develop");
        logger.info(res.toString());
    }
}
