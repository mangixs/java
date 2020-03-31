package com.wx.demo.service.impl;

import com.wx.demo.entity.Menu;
import com.wx.demo.mapper.MenuMapper;
import com.wx.demo.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> getAllMenu(){
        List<Menu> res = menuMapper.getAllMenu();
        return res;
    }
}
