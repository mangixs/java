package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.YbProductStock;
import com.example.demo.mapper.YbProductStockMapper;
import com.example.demo.service.YbProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YbProductStockServiceImpl implements YbProductStockService {

    @Autowired
    private YbProductStockMapper ybProductStockMapper;

    @Override
    public Page<YbProductStock> getProducStock(String warehouseCode, Integer pageNumber, Integer pageSize){
        Page page = new Page(pageNumber, pageSize);
        List<YbProductStock> res = ybProductStockMapper.getStockByPage(warehouseCode, page);
        return page.setRecords(res);
    }
}
