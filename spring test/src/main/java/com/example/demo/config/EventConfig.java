package com.example.demo.config;

import com.example.demo.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这三个一定要配置成bean
 *
 * @author dax
 * @since 2019/7/8 22:16
 */
@Configuration
public class EventConfig {

    @Bean
    public ApplicationEvent eatEvent() {
        return new EatEvent(true);
    }

    @Bean
    public ApplicationListener eatEventListener() {
        return new EatEventListener();
    }

    @Bean
    public ApplicationEventPublisherAware eatEventPublisherAware(ApplicationEvent eatEvent) {
        return new EatEventPublisherAware(eatEvent);
    }

}
