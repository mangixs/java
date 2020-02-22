package com.example.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * {@link EatEvent}事件的专属事件监听器
 * @author dax
 * @since 2019/7/8 22:11
 */
@Slf4j
public class EatEventListener implements ApplicationListener<EatEvent> {
    @Override
    public void onApplicationEvent(EatEvent eatEvent) {
        //如果吃完饭了
        if (eatEvent.isEatFinished()) {
            eatEvent.callGirlFriend();
            log.error("来自母老虎的咆哮：滚犊子");
            eatEvent.callBrothers();
            log.error("太晚了，我们已经满了，明天带你");
            log.info("还是关注一下 【码农小胖哥】 学习点新知识吧");
        }
    }
}
