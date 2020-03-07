package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.YbProductStock;

public interface YbProductStockService {
    Page<YbProductStock> getProducStock(String warehouseCode, Integer page, Integer pageSize);
}
