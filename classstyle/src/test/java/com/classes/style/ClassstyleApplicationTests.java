package com.classes.style;

import com.classes.style.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ClassstyleApplicationTests {

    @Test
    void contextLoads() {
        String time = DateUtil.getTimeNow();
        System.out.print(time);
    }

}
