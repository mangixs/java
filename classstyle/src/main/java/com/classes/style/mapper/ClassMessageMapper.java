package com.classes.style.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.entity.ClassMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMessageMapper extends BaseMapper<ClassMessage> {
    List<ClassMessage> getMessageList(Page page);
}
