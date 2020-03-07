package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Mongo.YbWangjiToken;
import com.example.demo.entity.User;
import com.example.demo.entity.YbProductStock;
import com.example.demo.event.EatEventPublisherAware;
import com.example.demo.mongo.YbWangjiTokenMongoDao;
import com.example.demo.service.AsyncTestService;
import com.example.demo.service.UserService;
import com.example.demo.service.YbProductStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;


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

    @Autowired
    private AsyncTestService asyncTestService;

    @Test
    public void asyncTest(){
        for (int i = 0; i <= 10; i++) {
            asyncTestService.asyncTest(i);
        }
    }

    @Autowired
    private UserService userService;

    @Test
    public void usertTest() throws Exception {
        User user = userService.getUserById(32);
        logger.info(user.toString());
    }

    @Autowired
    private YbProductStockService ybProductStockService;

    @Test
    public void productTest(){
        Page<YbProductStock> res =  ybProductStockService.getProducStock("GC_USEA", 1, 100);
        logger.info(JSONObject.toJSONString(res));
    }

    @Test
    public void filePath () throws IOException {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String serverpath= ResourceUtils.getFile("classpath:").getPath();

        logger.info(System.getProperty("user.dir") + "\\src\\main\\resources\\");
//        String aa = new ClassPathResource("static").getFile().getPath();
//        logger.info(aa);


    }
}
