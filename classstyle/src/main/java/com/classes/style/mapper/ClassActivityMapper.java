package com.classes.style.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.entity.ClassActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassActivityMapper extends BaseMapper<ClassActivity> {
    List<ClassActivity> getActivityList(Page page);

    List<ClassActivity> getPlanActivityList(Page page);

    List<ClassActivity> getArchiveList(Page page);
}
