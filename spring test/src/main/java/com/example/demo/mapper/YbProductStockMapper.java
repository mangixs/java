package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.YbProductStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YbProductStockMapper extends BaseMapper<YbProductStock> {
    List<YbProductStock> getStockByPage(@Param("warehouseCode") String warehouseCode, Page page);
}
