package com.example.demo.service.impl;

import com.example.demo.service.AsyncTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTestServiceImpl implements AsyncTestService {
    private final static Logger log = LoggerFactory.getLogger(AsyncTestServiceImpl.class);

    @Async
    @Override
    public void asyncTest(int i) {
        log.info("线程" + Thread.currentThread().getName() + "执行异步任务:" + i);
    }
}
