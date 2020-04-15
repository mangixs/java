package com.classes.style.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.entity.ClassArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassArticleMapper extends BaseMapper<ClassArticle> {
    List<ClassArticle> getArticleList(Page page);

    List<ClassArticle> getArticleTypeByPage(Page page, @Param("articleType") Integer articleType);
}
