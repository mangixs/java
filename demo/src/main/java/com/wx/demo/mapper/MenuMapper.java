package com.wx.demo.mapper;

import com.wx.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MenuMapper {

    List<Menu> getAllMenu();

    Menu getSingleMenu(int id);
}
