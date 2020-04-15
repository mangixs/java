package com.classes.style.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.entity.ClassNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassNewsMapper extends BaseMapper<ClassNews> {
    List<ClassNews> getNewsList(Page page);

    List<ClassNews> getNewsListByType(Page page, @Param("newType") Integer newType);
}
