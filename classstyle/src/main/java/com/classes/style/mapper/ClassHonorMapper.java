package com.classes.style.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.entity.ClassHonor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassHonorMapper extends BaseMapper<ClassHonor> {
    List<ClassHonor> getHonorList(Page page);
}
