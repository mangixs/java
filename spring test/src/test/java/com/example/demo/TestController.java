package com.example.demo;

import com.example.demo.entity.Mongo.YbWangjiToken;
import com.example.demo.event.EatEventPublisherAware;
import com.example.demo.mongo.YbWangjiTokenMongoDao;
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
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private YbWangjiTokenMongoDao ybWangjiTokenMongoDao;

    @Autowired
    private EatEventPublisherAware eatEventPublisherAware;

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

    @Test
    public void contextLoads() {
        eatEventPublisherAware.refreshEvent();
    }
}
