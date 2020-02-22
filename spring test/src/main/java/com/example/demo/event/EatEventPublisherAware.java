package com.example.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 发布事件 发布事件通过实现 事件发布接口 {@link ApplicationEventPublisher}
 * 或者通过门面接口{@link ApplicationEventPublisherAware}
 * 推荐按照下面的实现方式，而且该类需要注册为spring bean
 *
 * @author dax
 * @since 2019 /7/8 22:04
 */
@Slf4j
public class EatEventPublisherAware implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    private ApplicationEvent eatEvent;

    public EatEventPublisherAware(ApplicationEvent eatEvent) {
        this.eatEvent = eatEvent;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发送事件动作   事件的动作需要主动触发  调用此方法进行事件触发
     * 代理{@link ApplicationEventPublisher#publishEvent(ApplicationEvent)}
     */
    public void refreshEvent() {
        log.info("发送事件中……");
        this.applicationEventPublisher.publishEvent(eatEvent);
    }

}
