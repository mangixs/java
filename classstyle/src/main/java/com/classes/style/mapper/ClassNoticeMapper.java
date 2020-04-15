package com.classes.style.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.entity.ClassNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassNoticeMapper extends BaseMapper<ClassNotice> {
    List<ClassNotice> getNoticeList(Page page);
}
