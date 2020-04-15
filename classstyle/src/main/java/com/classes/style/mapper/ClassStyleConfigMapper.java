package com.classes.style.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classes.style.entity.ClassStyleConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassStyleConfigMapper extends BaseMapper<ClassStyleConfig> {
    List<ClassStyleConfig> getClassStyleConfigByName(@Param("list") List<String> list);

    void insertConfig(ClassStyleConfig classStyleConfig);

    void updateConfig(ClassStyleConfig classStyleConfig);

    ClassStyleConfig getClassConfigByCode(@Param("configName") String configName);
}
