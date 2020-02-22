package com.example.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 吃饭事件
 *
 * @author dax
 * @since 2019 /7/8 21:54
 */
@Slf4j
public class EatEvent extends ApplicationEvent {
    private Boolean eatFinished;


    /**
     * Instantiates a new Eat event.
     *
     * @param eatFinished 吃饭是否完成的信号 这里也可以传递其他资源
     */
    public EatEvent(Boolean eatFinished) {
        super(eatFinished);
        this.eatFinished = eatFinished;
    }

    /**
     * 这里会由对应监听器{@link ApplicationListener<EatEvent>} 执行
     *
     * 叫女友收拾碗筷.
     */
    public void callGirlFriend() {
        log.info("亲爱的！ 我吃完饭了，来收拾收拾吧");
    }

    /**
     * 这里会由对应监听器{@link ApplicationListener<EatEvent>} 执行
     * 呼叫兄弟开黑.
     */
    public void callBrothers() {
        log.info("兄弟们！ 我吃完饭了，带我开黑");
    }

    /**
     * 吃晚饭的信号.
     *
     * @return the boolean
     */
    public Boolean isEatFinished() {
        return this.eatFinished;
    }
}
