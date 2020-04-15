package com.classes.style.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.classes.style.entity.ClassMessage;

public interface ClassMessageService extends IService<ClassMessage> {
    void deleteMessage(Integer id);

    Page<ClassMessage> getMessageList(Integer pageNumber);

    Page<ClassMessage> getIndexMessageList(Integer pageNumber);

    void submitMessage(String msg, Integer anonymous);
}
